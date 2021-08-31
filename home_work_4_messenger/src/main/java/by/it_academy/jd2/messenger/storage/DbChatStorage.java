package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;

import java.util.List;

public class DbChatStorage implements IChatStorage {
    private static final DbChatStorage instance = new DbChatStorage();

    public static DbChatStorage getInstance() {
        return instance;
    }

    @Override
    public List<Message> get(String login) {
        return null;
    }

    @Override
    public void addMessage(String login, Message message) {

    }
}
