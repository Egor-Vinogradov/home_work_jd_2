package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage implements IUserStorage {
    /**
     * Переменная для синглтона
     */
    private static final UserStorage instance = new UserStorage();

    /**
     * Переменная для хранения коллекции юзеров. Ключ - логин
     */
    private final Map<String, User> users = new HashMap<>();

    /**
     * Метод возвращает юзера из коллекции. Поиск по ключу - логину
     * @param login логин
     * @return юзер
     */
    @Override
    public User get(String login) {
        return this.users.get(login);
    }

    /**
     * Метод для добавления юзера в хранилище
     * @param user юзер
     */
    @Override
    public void add(User user) {
        users.put(user.getLogin(), user);
    }

    /**
     * Метод возвращает коллекцию юзеров
     * @return коллекция
     */
    @Override
    public Collection<User> getAll() {
        return this.users.values();
    }

    /**
     * Метод для создания, либо вызова синглтона
     * @return возвращает экземпляр синглтона
     */
    public static UserStorage getInstance() {
        return instance;
    }
}
