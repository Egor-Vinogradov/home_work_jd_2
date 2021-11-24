package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IWeighingDiaryDao;
import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.service.api.IWeighingActiveJournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class WeighingJournalService implements IWeighingActiveJournalService<Weighing> {

    private final IWeighingDiaryDao repository;
    private final ProfileService profileService;

    public WeighingJournalService(IWeighingDiaryDao repository, ProfileService profileService) {
        this.repository = repository;
        this.profileService = profileService;
    }

    @Override
    public Weighing addItem(Long idProfile, WeighingActiveJournalDto dto) {
        try {
            Weighing weighingNew = new Weighing();
            Profile profile = chekProfile(idProfile, idProfile);
            weighingNew.setProfile(profile);
            weighingNew.setWeight(dto.getWeight());
            weighingNew.setCreationDate(
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.getCreationDate()),
                            ZoneId.systemDefault()));
            return this.repository.save(weighingNew);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public Weighing getItem(Long idProfile, Long id) {
        try {
            return this.repository.findByProfileIdAndId(idProfile, id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public Profile chekProfile(Long idProfile, Long verifiableId) {
        if (idProfile.equals(verifiableId)) {
            try {
                return this.profileService.findByProfileSearch(idProfile);
            } catch (Exception e) {
                throw new IllegalArgumentException("Профиль не найден");
            }
        } else {
            throw new IllegalArgumentException("Задан ID другого профиля");
        }
    }

    @Override
    public List<Weighing> getAllItems(Long idProfile, SearchFilter filter) {
        int offset = Math.toIntExact((filter.getPage() != null) ? filter.getPage() : 0);
        int limit = Math.toIntExact((filter.getSize() != null) ? filter.getSize() : Integer.MAX_VALUE);

        Profile profile = chekProfile(idProfile, idProfile);

        if (filter.getDataStart() != null && filter.getDataEnd() != null) {
            LocalDateTime dataStart = LocalDateTime.ofInstant(Instant.ofEpochMilli(filter.getDataStart()),
                    ZoneId.systemDefault());
            LocalDateTime dataEnd = LocalDateTime.ofInstant(Instant.ofEpochMilli(filter.getDataEnd()),
                    ZoneId.systemDefault());

            return this.repository.findAllByCreationDateBetweenAndProfile(dataStart,
                    dataEnd, profile, PageRequest.of(offset, limit));
        }


        try {
            return this.repository.findAllByProfile(profile, PageRequest.of(offset, limit));
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public Weighing updateItem(Long idProfile, Long id,
                               Long version, Weighing item) {
        try {
            Weighing weighingUpdate = this.repository.findById(id).get();
            chekProfile(idProfile, weighingUpdate.getProfile().getId());
            weighingUpdate.setWeight(item.getWeight());
            weighingUpdate.setCreationDate(item.getCreationDate());

            if (weighingUpdate.getVersion() > version) {
                throw new OptimisticLockException("Просиходило изменение");
            }

            weighingUpdate.setUpdateDate(LocalDateTime.now());
            return this.repository.save(weighingUpdate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public boolean deleteItem(Long idProfile, Long id, Long version) throws OptimisticLockException {
        try {
            Weighing weighing = this.repository.findById(id).get();
            chekProfile(idProfile, weighing.getProfile().getId());
            if(weighing.getVersion() > version) {
                throw new OptimisticLockException("Просиходило изменение");
            }
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // для проверки. заполнение дефолтных данных
    public void startInit() {
        WeighingActiveJournalDto weighingActiveJournalDto = new WeighingActiveJournalDto();
        weighingActiveJournalDto.setCreationDate(System.currentTimeMillis() - 86400000);
        weighingActiveJournalDto.setWeight(80.0);

        Weighing weighing = null;

        try {
            weighing = this.repository.findById(1L).get();
        } catch (Exception e) {
            addItem(1L, weighingActiveJournalDto);
            addItem(2L, weighingActiveJournalDto);

            weighingActiveJournalDto.setCreationDate(System.currentTimeMillis());
            weighingActiveJournalDto.setWeight(85.0);
            addItem(1L, weighingActiveJournalDto);
            addItem(2L, weighingActiveJournalDto);
        }

    }
}
