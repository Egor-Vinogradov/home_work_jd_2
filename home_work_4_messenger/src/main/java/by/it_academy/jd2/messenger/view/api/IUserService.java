package by.it_academy.jd2.messenger.view.api;

import by.it_academy.jd2.messenger.model.User;

import java.util.Collection;

public interface IUserService {
    User get(String login);
    void signUp(User user);
    Collection<User> getAll();
}
