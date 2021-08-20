package by.it_academy.jd2.messenger.view.api;

import by.it_academy.jd2.messenger.model.User;

public interface IAuthService {
    User auth(String login, String pass);
}
