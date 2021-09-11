package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.storage.ConfigDBStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

public class DBInitializer {

    private static DBInitializer instance;
    private static ConfigDBStorage configDBStorage;

    private static Connection connection;
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public Connection getConnection() {
        configDBStorage = ConfigDBStorage.getInstance();
        ConfigDB configDB = configDBStorage.getConfigDB();

        try {
            dataSource.setDriverClass(configDB.getDriver());
            dataSource.setJdbcUrl(configDB.getUrl());
            dataSource.setUser(configDB.getUser_name());
            dataSource.setPassword(configDB.getPassword());
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxStatements(100);

            connection = dataSource.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return connection;
    }

    public static DBInitializer getInstance() {
        if (instance == null) {
            synchronized (DBInitializer.class) {
                instance = new DBInitializer();
            }
        }
        return instance;
    }

}

