package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.config.MailConfig;
import by.it_academy.jd2.food_control.dao.api.IUserDao;
import by.it_academy.jd2.food_control.dto.LoginDto;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.api.ERoleUser;
import by.it_academy.jd2.food_control.model.api.EUserStatus;
import by.it_academy.jd2.food_control.config.security.SecurityConfig;
import by.it_academy.jd2.food_control.service.api.IUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserDao repository;
    private final SecurityConfig securityConfig;
    private final MailConfig mailConfig;

    public UserService(IUserDao repository, SecurityConfig securityConfig, MailConfig mailConfig) {
        this.repository = repository;
        this.securityConfig = securityConfig;
        this.mailConfig = mailConfig;
    }

    @Override
    public LoginDto authentication(LoginDto loginDto) {
        LoginDto newLoginDto = new LoginDto();
        try {
            User user = findByLogin(loginDto.getLogin());
            if (this.securityConfig.passwordEncoder().matches(loginDto.getPassword(), user.getPassword())) {
                newLoginDto.setLogin(user.getLogin());
                newLoginDto.setName(user.getName());
                newLoginDto.setStatus(user.getStatus());
                newLoginDto.setRole(user.getRole());
                newLoginDto.setToken(this.securityConfig.getJWTToken(user.getLogin(), user.getRole()));
                return newLoginDto;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка аутентификации " + e);
            return null;
        }
    }

    @Override
    public LoginDto confirmationRegistration(String login) {
        User user = findByLogin(login);
        LoginDto loginDto = new LoginDto();
        user.setUpdateDate(LocalDateTime.now());
        user.setStatus(EUserStatus.ACTIVE);
        try {
            addItem(user);
            return loginDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось обновить статус");
        }
    }

    @Override
    public LoginDto registration(LoginDto loginDto) {
        User user = new User();
        user.setName(loginDto.getName());
        user.setPassword(this.securityConfig.passwordEncoder().encode(loginDto.getPassword()));
        user.setLogin(loginDto.getLogin());
        user.setStatus(EUserStatus.NOT_ACTIVE);
        user.setRole(ERoleUser.ROLE_USER);

        LoginDto newLoginDto = new LoginDto();
        try {
            Long id = this.addItem(user);
            newLoginDto.setName(user.getName());
            newLoginDto.setLogin(user.getLogin());
            newLoginDto.setStatus(user.getStatus());

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(user.getLogin());
            message.setSubject("Подтверждение регистрации");
            message.setText("Для окончания регистрации пройдите по ссылке: http://localhost:8080/api/user?login=" + user.getLogin());

            this.mailConfig.getJavaMailSender().send(message);

            return newLoginDto;
        } catch (Exception e) {
            System.out.println("Ошибка регистрации " + e);
            return null;
        }
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
        int offset = (int) filter.getPage();
        int limit = (int) filter.getSize();

        try {
            return this.repository.findByName(filter.getName(), PageRequest.of(offset, limit));
        } catch (Exception e) {
            return null;
        }
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

    public User findByLogin(String login) {
        try {
            return this.repository.findByLogin(login).get(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Пользователь с таким именем не найден");
        }
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
                user.setLogin("admin@mail.ru");
                user.setPassword(this.securityConfig.passwordEncoder().encode("111"));
                user.setName("Администратор Администратович");
                user.setRole(ERoleUser.ROLE_ADMIN);
                user.setStatus(EUserStatus.ACTIVE);
                user.setCreationDate(LocalDateTime.now());
                addItem(user);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка БД");
            }
        }

        try {
            user = new User();
            user.setLogin("egor@soooperfekt.by");
            user.setPassword(this.securityConfig.passwordEncoder().encode("111"));
            user.setName("Егор");
            user.setRole(ERoleUser.ROLE_USER);
            user.setStatus(EUserStatus.ACTIVE);
            user.setCreationDate(LocalDateTime.now());
            addItem(user);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Ошибка БД");
        }
    }
}
