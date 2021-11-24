package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IJournalFoodDao;
import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.JournalFood;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.api.EEating;
import by.it_academy.jd2.food_control.service.api.IWeighingActiveJournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class JournalFoodService implements IWeighingActiveJournalService<JournalFood> {

    private final IJournalFoodDao repository;
    private final ProfileService profileService;

    public JournalFoodService(IJournalFoodDao repository, ProfileService profileService) {
        this.repository = repository;
        this.profileService = profileService;
    }

    @Override
    public JournalFood addItem(Long idProfile, WeighingActiveJournalDto dto) {
        try {
            JournalFood journalFood = new JournalFood();
            Profile profile = chekProfile(idProfile, idProfile);
            journalFood.setEating(dto.getEating());
            journalFood.setProfile(profile);
            if (dto.getDish() != null && dto.getProduct() != null) {
                throw new IllegalArgumentException("Укажите что-то одно");
            }
            journalFood.setProduct((dto.getProduct() == null) ? null : dto.getProduct());
            journalFood.setDish((dto.getDish() == null) ? null : dto.getDish());
            journalFood.setMeasure(dto.getMeasure());
            journalFood.setCreationDate(LocalDateTime.now());


            return this.repository.save(journalFood);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public JournalFood getItem(Long idProfile, Long id) {
        try {
            return this.repository.findByProfileIdAndId(idProfile, id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public List<JournalFood> getAllItems(Long idProfile, SearchFilter filter) {
        int offset = Math.toIntExact((filter.getPage() != null) ? filter.getPage() : 0);
        int limit = Math.toIntExact((filter.getSize() != null) ? filter.getSize() : Integer.MAX_VALUE);

        Profile profile = chekProfile(idProfile, idProfile);

        if (filter.getDay() != null) {
            LocalDateTime day = LocalDateTime.ofInstant(Instant.ofEpochMilli(filter.getDay()),
                    ZoneId.systemDefault());
            LocalDateTime dataStart = day.toLocalDate().atStartOfDay();
            LocalDateTime dataEnd = day.toLocalDate().atStartOfDay().plusDays(1).minusSeconds(1);

            return this.repository.findAllByCreationDateBetweenAndProfile(dataStart,
                    dataEnd, profile);

        }

        try {
            return this.repository.findAllByProfile(profile, PageRequest.of(offset, limit));
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public JournalFood updateItem(Long idProfile, Long id, Long version, JournalFood item) {
        try {
            JournalFood food = this.repository.findById(id).get();
            chekProfile(idProfile, item.getProfile().getId());

            if (item.getDish() != null && item.getProduct() != null) {
                throw new IllegalArgumentException("Укажите что-то одно");
            }

            if (food.getVersion() > version) {
                throw new OptimisticLockException("Просиходило изменение");
            }

            food.setEating(item.getEating());
            food.setProduct(item.getProduct());
            food.setDish(item.getDish());
            food.setMeasure(item.getMeasure());

            food.setUpdateDate(LocalDateTime.now());
            return this.repository.save(food);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public boolean deleteItem(Long idProfile, Long id, Long version) {
        try {
            JournalFood food = this.repository.findById(id).get();
            chekProfile(idProfile, food.getProfile().getId());
            if(food.getVersion() > version) {
                throw new OptimisticLockException("Просиходило изменение");
            }
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
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

    public void startInit() {
        JournalFood journalFood = null;

        try {
            journalFood = this.repository.findById(1L).get();
        } catch (Exception e) {
            try {
                journalFood = new JournalFood();
                journalFood.setEating(EEating.BREAKFAST);
                Profile profile = chekProfile(1L, 1L);
                journalFood.setProfile(profile);
                journalFood.setProduct(null);
                journalFood.setDish(null);
                journalFood.setMeasure(100);
                journalFood.setCreationDate(LocalDateTime.now());
                this.repository.save(journalFood);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка работы с БД");
            }
        }
    }
}
