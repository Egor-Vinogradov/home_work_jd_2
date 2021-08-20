package by.it_academy.jd2.messenger.view;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.ChatStorage;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.messenger.view.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {
    /**
     * Переменная для синглтона
     */
    private final static MessageService instance = new MessageService();

    /**
     * Переменная для хранилища сообщений
     */
    private final IChatStorage chatStorage;

    /**
     * При инициализации сервиса вызываем хранилище
     */
    private MessageService() {
        this.chatStorage = ChatStorage.getInstance();
    }

    /**
     * Метод получает переписку определенного юзера
     * @param user юзер
     * @return коллекция с перепиской
     */
    @Override
    public List<Message> get(User user) {
        return this.chatStorage.get(user.getLogin());
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
