package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatStorage implements IChatStorage {
    /**
     * Переменная для синглтона
     */
    private final static ChatStorage instance = new ChatStorage();

    /**
     * Коллекция для сохранения чата с пользователем
     * Ключ - логин пользователя
     */
    private final Map<String, List<Message>> chats = new HashMap<>();

    /**
     * Метод получает коллекцию с перепиской по логину пользователя
     * @param login логин
     * @return возвращает переписку
     */
    @Override
    public List<Message> get(String login) {
        return this.chats.get(login);
    }

    /**
     * Метод добавляет сообщение в чат (ищет по логину пользователя)
     * Если нет чата, то создает новый и добавляет сообщение
     * @param login логин
     * @param message сообщение
     */
    @Override
    public void addMessage(String login, Message message) {
        List<Message> list;
        // проверяем на наличие чата и если есть, то вызываем его. если нет, тогда создаем новый
        if (this.chats.containsKey(login)) {
            list = this.chats.get(login);
        } else {
            list = new ArrayList<Message>();
            this.chats.put(login, list);
        }
        list.add(message);
    }

    /**
     * Метод для создания, либо вызова синглтона
     * @return возвращает экземпляр синглтона
     */
    public static ChatStorage getInstance() {
        return instance;
    }
}
