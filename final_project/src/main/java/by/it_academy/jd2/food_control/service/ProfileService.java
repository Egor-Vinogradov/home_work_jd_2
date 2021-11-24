package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IProfileDao;
import by.it_academy.jd2.food_control.dto.ProfileDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.model.api.EActivity;
import by.it_academy.jd2.food_control.model.api.ESex;
import by.it_academy.jd2.food_control.model.api.ETarget;
import by.it_academy.jd2.food_control.service.api.*;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IService<Profile, Long>, ILoginSearch<Profile>,
        IProfileSearch<Profile> {

    private final IProfileDao repository;
    private final UserService userService;
    private final ISearchProfileCreatedDate<Weighing> searchProfileCreatedDate;

    public ProfileService(IProfileDao repository,
                          UserService userService,
                          ISearchProfileCreatedDate<Weighing> searchProfileCreatedDate) {
        this.repository = repository;
        this.userService = userService;
        this.searchProfileCreatedDate = searchProfileCreatedDate;
    }

    @Override
    public Long addItem(Profile item) {
        // дефолтный профиль
        if (item.getUser() != null) {
            Profile profile = new Profile();
            profile.setDateOfBirth(LocalDateTime.now());
            profile.setSex(ESex.MAN);
            profile.setActivity(EActivity.MOBILE);
            profile.setTarget(ETarget.MAINTAINING);
            profile.setUser(item.getUser());
            profile.setCreationDate(LocalDateTime.now());
            return this.repository.save(profile).getId();
        }
        return null;
    }

    @Override
    public Profile findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Профиль с таким id не найден")
        );
    }

    @Override
    public List<Profile> findAll(SearchFilter filter) {
        return null;
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        return false;
    }

    @Override
    public Profile updateItem(Long id, Profile item, Long version) {
        Profile profile = findById(id);
        profile.setUpdateDate(LocalDateTime.now());
        profile.setSex(item.getSex());
        profile.setDateOfBirth(item.getDateOfBirth());
        profile.setActivity(item.getActivity());
        profile.setTarget(item.getTarget());
        profile.setTargetWeight(item.getTargetWeight());
        profile.setHeight(item.getHeight());

        if (profile.getVersion() > version) {
            throw new OptimisticLockException();
        }

        try {
            return this.repository.save(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка работы с БД");
        }
    }

    @Override
    public Profile findByLogin(String login) {
        try {
            User user = this.userService.findByLogin(login);
            return this.repository.findByUser(user).get(0);
        } catch (Exception e) {
            return null;
        }
    }


    public ProfileDto adapterProfile(Profile profile) {
        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setActivity(profile.getActivity());
        Weighing weight = this.searchProfileCreatedDate.findFirstCreationDate(profile.getId(),
                LocalDateTime.now());
        dto.setWeight((weight != null) ? weight.getWeight() : null);
        dto.setSex(profile.getSex());
        dto.setTarget(profile.getTarget());
        dto.setTargetWeight(profile.getTargetWeight());
        dto.setHeight(profile.getHeight());
        dto.setUser(profile.getUser());
        dto.setCreationDate(profile.getCreationDate());
        dto.setUpdateDate(profile.getUpdateDate());
        dto.setVersion(profile.getVersion());

        return dto;
    }

    @Override
    public Profile findByProfileSearch(Long idProfile) {
        try {
            return this.repository.findById(idProfile).get();
        } catch (Exception e) {
            throw new IllegalArgumentException("Профиль с таким id не найден");
        }
    }

    public void startInit() {
        Profile profile = null;

        try {
            profile = findById(1L);
        } catch (Exception e) {
            try {
                profile = new Profile();
                profile.setUser(this.userService.findById(1L));
                addItem(profile);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка БД");
            }
        }

        try {
            profile = findById(2L);
        } catch (Exception e) {
            try {
                profile = new Profile();
                profile.setUser(this.userService.findById(2L));
                addItem(profile);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка БД");
            }
        }
    }
}
