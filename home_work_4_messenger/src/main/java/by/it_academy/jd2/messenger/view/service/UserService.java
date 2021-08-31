package by.it_academy.jd2.messenger.view.service;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.MemoryUserStorage;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.messenger.view.api.IMessageService;
import by.it_academy.jd2.messenger.view.api.IUserService;

import java.util.Collection;
import java.util.Date;

public class UserService extends MemoryUserStorage implements IUserService {
    /**
     * Переменная для синглтона
     */
    private static final UserService instance = new UserService();

    /**
     * Переменные для хранилища пользователя и сообщений
     */
    private IUserStorage userStorage;
    private final IMessageService messageService;

    public UserService() {
        this.messageService = MessageService.getInstance();
    }

    @Override
    public void setUserStorage(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    /**
     * Метод возращает юзера, перенаправляя поиск на хранилище
     * @param login логин
     * @return юзер
     */
    @Override
    public User get(String login) {
        return this.userStorage.get(login);
    }

    /**
     * Метод сохраняет пользователя в хранилище
     * @param user
     */
    @Override
    public void signUp(User user) {
        // проверяет на заполнение поля юзера
        this.checkOnNullUser(user);
        // сохраняет юзера
        this.userStorage.add(user);

        /**
         * Записываем первое сообщение новому юзеру
         */
        Message message = new Message();
        message.setFrom(user.getLogin());
        message.setSendDate(new Date());
        message.setText("Hello, " + user.getFio());

        this.messageService.addMessage(user, message);
    }

    /**
     * Метод возвращает коллекцию юзеров
     * @return
     */
    @Override
    public Collection<User> getAll() {
        return this.userStorage.getAll();
    }

    /**
     * Вспомогательный метод для проверки на null и заполнение
     * @param volume строковый параметр для проверки
     * @return true либо false если не заполнен параметр
     */
    private boolean checkOnNull(String volume){
        return volume == null || volume.isEmpty();
    }

    /**
     * Метод проверяет на заполняемость полей формы регистрации
     * Собирает сообщение, если поле не заполнено и выкидывает исключение с сообщением о пустых полях
     * @param user
     */
    private void checkOnNullUser(User user) {
        String message = "";
        if(checkOnNull(user.getLogin())){
            message += "Не заполнен логин";
        }
        if(this.checkOnNull(user.getPassword())){
            if(!message.isEmpty()){
                message += "; ";
            }
            message += "Не заполнен пароль";
        }
        if(this.checkOnNull(user.getFio())){
            if(!message.isEmpty()){
                message += "; ";
            }
            message += "Не заполнено ФИО";
        }
        if(!message.isEmpty()){
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Метод для создания, либо вызова синглтона
     * @return возвращает экземпляр синглтона
     */
    public static UserService getInstance() {
        return instance;
    }
}
