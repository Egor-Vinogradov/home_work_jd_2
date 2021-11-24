package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IActiveDao;
import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Active;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.service.api.IWeighingActiveJournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ActiveJournalService implements IWeighingActiveJournalService<Active> {

    private final IActiveDao repository;
    private final ProfileService profileService;

    public ActiveJournalService(IActiveDao repository, ProfileService profileService) {
        this.repository = repository;
        this.profileService = profileService;
    }

    @Override
    public Active addItem(Long idProfile, WeighingActiveJournalDto dto) {
        try {
            Active active = new Active();
            Profile profile = chekProfile(idProfile, idProfile);
            active.setCreationDate(LocalDateTime.now());
            active.setName(dto.getName());
            active.setProfile(profile);
            active.setCalories(dto.getCalories());

            return this.repository.save(active);
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
    public Active getItem(Long idProfile, Long id) {
        try {
            return this.repository.findByProfileIdAndId(idProfile, id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public List<Active> getAllItems(Long idProfile, SearchFilter filter) {
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
    public Active updateItem(Long idProfile, Long id, Long version, Active item) throws IllegalArgumentException {
        try {
            Active active = this.repository.findById(id).get();
            chekProfile(idProfile, active.getProfile().getId());
            active.setName(item.getName());
            active.setCalories(item.getCalories());

            if (active.getVersion() > version) {
                throw new OptimisticLockException();
            }

            active.setUpdateDate(LocalDateTime.now());
            return this.repository.save(active);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public boolean deleteItem(Long idProfile, Long id, Long version) throws OptimisticLockException {
        try {

            Active active = this.repository.findById(id).get();
            chekProfile(idProfile, active.getProfile().getId());

            if (active.getVersion() > version) {
                throw new OptimisticLockException("Просиходило изменение");
            }

            this.repository.delete(active);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void startInit() {
        WeighingActiveJournalDto dto = new WeighingActiveJournalDto();
        dto.setName("active 1");
        dto.setCalories(250.0);
        addItem(1L, dto);
        addItem(2L, dto);
        dto.setName("active 2");
        dto.setCalories(350.0);
        addItem(1L, dto);
        addItem(2L, dto);
    }
}
