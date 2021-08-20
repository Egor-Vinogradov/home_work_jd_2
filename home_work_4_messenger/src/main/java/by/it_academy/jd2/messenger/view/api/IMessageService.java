package by.it_academy.jd2.messenger.view.api;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;

import java.util.List;

public interface IMessageService {
    List<Message> get(User user);
    void addMessage(String login, Message message);
    void addMessage(User user, Message message);
}
