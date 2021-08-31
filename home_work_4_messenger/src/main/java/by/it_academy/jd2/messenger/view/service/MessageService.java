package by.it_academy.jd2.messenger.view.service;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.DbChatStorage;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.messenger.view.api.IMessageService;

import java.util.List;

public class MessageService extends DbChatStorage implements IMessageService {
    /**
     * Переменная для синглтона
     */
    private final static MessageService instance = new MessageService();

    /**
     * Переменная для хранилища сообщений
     */
    private IChatStorage chatStorage;

    @Override
    public void setChatStorage(IChatStorage chatStorage) {
        this.chatStorage = chatStorage;
    }


    @Override
    public List<Message> get(String login) {
        return this.chatStorage.get(login);
    }

    /**
     * Метод добавляет сообщение в хранилище
     * Поиск хранилища по логину юзера
     * @param login логин юзера
     * @param message сообщение
     */
    @Override
    public void addMessage(String login, Message message) {
        this.chatStorage.addMessage(login, message);
    }

    /**
     * Метод добавляет сообщение в хранилище
     * Поиск хранилища по юзеру - логину
     * @param user юзер
     * @param message сообщение
     */
    @Override
    public void addMessage(User user, Message message) {
        this.chatStorage.addMessage(user.getLogin(), message);
    }

    /**
     * Метод для создания, либо вызова синглтона
     * @return возвращает экземпляр синглтона
     */
    public static MessageService getInstance() {
        return instance;
    }
}
