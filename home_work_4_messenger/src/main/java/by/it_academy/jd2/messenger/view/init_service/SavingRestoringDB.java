package by.it_academy.jd2.messenger.view.init_service;

import by.it_academy.jd2.messenger.model.ConfigDB;
import by.it_academy.jd2.messenger.storage.ConfigDBStorage;
import by.it_academy.jd2.messenger.storage.DbChatStorage;
import by.it_academy.jd2.messenger.storage.DbUserStorage;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.messenger.view.api.IMessageService;
import by.it_academy.jd2.messenger.view.api.IStorageService;
import by.it_academy.jd2.messenger.view.api.IUserService;
import by.it_academy.jd2.messenger.view.service.MessageService;
import by.it_academy.jd2.messenger.view.service.UserService;

public class SavingRestoringDB extends DbChatStorage implements IStorageService {
    private static final SavingRestoringDB instance = new SavingRestoringDB();

    private ConfigDBStorage configDBStorage;
    private final IChatStorage chatStorage;
    private final IUserStorage userStorage;
    private IMessageService messageService;
    private IUserService userService;


    private static final String USER_MAME = "postgres";
    private static final String PASSWORD = "egor";
    private static final String URL = "jdbc:postgresql://localhost:5432/messanger";
    private static final String DRIVER = "org.postgresql.Driver";

    private SavingRestoringDB() {
        this.configDBStorage = ConfigDBStorage.getInstance();
        this.chatStorage = DbChatStorage.getInstance();
        this.userStorage = DbUserStorage.getInstance();

        this.messageService = MessageService.getInstance();
        this.messageService.setChatStorage(this.chatStorage);
        this.userService = UserService.getInstance();
        this.userService.setUserStorage(this.userStorage);
    }

    @Override
    public void saveData() {

    }

    @Override
    public void initData() {
        ConfigDB configDB = new ConfigDB();
        configDB.setUser_name(USER_MAME);
        configDB.setPassword(PASSWORD);
        configDB.setUrl(URL);
        configDB.setDriver(DRIVER);

        configDBStorage.add(configDB);
    }

    public static SavingRestoringDB getInstance() {
        return instance;
    }
}
