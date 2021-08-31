package by.it_academy.jd2.messenger.view.api;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;

import java.util.List;

public interface IMessageService {
    List<Message> get(String login);
    void addMessage(String login, Message message);
    void addMessage(User user, Message message);
    void setChatStorage(IChatStorage chatStorage);
}
