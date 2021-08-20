package by.it_academy.jd2.messenger.storage.api;

import by.it_academy.jd2.messenger.model.User;

import java.util.Collection;

public interface IUserStorage {
    User get(String login);
    void add(User user);
    Collection<User> getAll();
}
