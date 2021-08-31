package by.it_academy.jd2.messenger.view.service;

import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.view.api.IAuthService;
import by.it_academy.jd2.messenger.view.api.IUserService;

import java.util.Objects;

public class AuthService implements IAuthService {
    /**
     * Переменная для синглтона
     */
    private final static AuthService instance = new AuthService();

    /**
     * Переменная для инициализации userService
     */
    private final IUserService userService;

    public AuthService() {
        /**
         * Инициализируем userService при создании экзмепляра класса
         */
        this.userService = UserService.getInstance();
    }

    /**
     * Метод для создания, либо вызова синглтона
     * @return возвращает экземпляр синглтона
     */
    public static AuthService getInstance() {
        return instance;
    }

    /**
     * Метод при передаче логина возвращает user. Также проверяет пароль
     * @param login логин
     * @param pass пароль
     * @return возвращает user
     */
    @Override
    public User auth(String login, String pass) {
        // получаем user по логину
        User user = this.userService.get(login);

        // возращаем null если user не найден
        if(user == null){
            return null;
        }

        // возвращаем null если пароль не совпадает
        if(!Objects.equals(user.getPassword(), pass)){
            return null;
        }

        return user;
    }
}
