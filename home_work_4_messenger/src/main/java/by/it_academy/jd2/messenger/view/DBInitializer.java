package by.it_academy.jd2.messenger.view;

import by.it_academy.jd2.messenger.model.ConfigDB;
import by.it_academy.jd2.messenger.storage.ConfigDBStorage;
import by.it_academy.jd2.messenger.view.init_service.SavingRestoringDB;

import java.sql.*;

public class DBInitializer {

    private static final DBInitializer instance = new DBInitializer();

    private static ConfigDBStorage configDBStorage;

    private static final String DRIVER = "org.postgresql.Driver";

    private static Connection connection;

    public Connection getConnection() {
        configDBStorage = ConfigDBStorage.getInstance();
        ConfigDB configDB = configDBStorage.getConfigDB();

        try {
            connection = DriverManager.getConnection(configDB.getUrl(), configDB.getUser_name(),
                    configDB.getPassword());
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
        return connection;
    }

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Ошибка загрузки драйвера БД", e);
        }
    }

    public static DBInitializer getInstance() {
        return instance;
    }

}
