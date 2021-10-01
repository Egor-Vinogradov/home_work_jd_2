package by.it_academy.jd2.crm.controllers.listeners;

import by.it_academy.jd2.crm.model.ConfigDB;
import by.it_academy.jd2.crm.service.DBInitializer;
import by.it_academy.jd2.crm.service.hibernate.HibernateUtil;
import by.it_academy.jd2.crm.service.hibernate.InitHibDB;
import by.it_academy.jd2.crm.storage.ConfigDBStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DefaultInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigDBStorage configDBStorage = ConfigDBStorage.getInstance();

        String DRIVER = "org.postgresql.Driver";
        String USER_MAME = "root";
        String PASSWORD = "root";
        String URL = "jdbc:postgresql://localhost:5432/crm";

        ConfigDB configDB = new ConfigDB();
        configDB.setDriver(DRIVER);
        configDB.setUrl(URL);
        configDB.setUser_name(USER_MAME);
        configDB.setPassword(PASSWORD);

        configDBStorage.add(configDB);

        InitHibDB.createTable();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBInitializer.shutdown();
        HibernateUtil.shutdown();
    }
}
