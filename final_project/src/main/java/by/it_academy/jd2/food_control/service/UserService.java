package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IUserDao;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.model.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IService<User, Long> {

    private final IUserDao repository;

    public UserService(IUserDao repository) {
        this.repository = repository;
    }

    @Override
    public Long addItem(User item) {
        item.setCreationDate(LocalDateTime.now());
        return this.repository.save(item).getId();
    }

    @Override
    public User findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Пользователь по id не найден")
        );
    }

    @Override
    public List<User> findAll(SearchFilter filter) {
        return null;
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        return false;
    }

    @Override
    public User updateItem(Long id, User item) {
        item.setUpdateDate(LocalDateTime.now());
        return null;
    }

    /**
     * как временное явление
     * анотация говорит о том, что метод запуститься сразу после внедения сервиса
     * один раз!!! и один только такой метод может быть
     * можно было стартануть в конструкторе
     */
    @PostConstruct
    private void startInit() {
        User user = null;
        try {
            user = findById(1L);
        } catch (Exception e) {
            try {
                user = new User();
                user.setName("admin");
                addItem(user);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка БД");
            }
        }
    }
}
