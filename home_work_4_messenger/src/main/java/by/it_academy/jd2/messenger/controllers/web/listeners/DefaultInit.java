package by.it_academy.jd2.messenger.controllers.web.listeners;

import by.it_academy.jd2.messenger.model.About;
import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.*;
import by.it_academy.jd2.messenger.view.init_service.SavingRestoringDataFile;
import by.it_academy.jd2.messenger.view.api.IStorageService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class DefaultInit extends SavingRestoringDataFile implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MemoryUserStorage userStorage = MemoryUserStorage.getInstance();

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setFio("admin");
        user.setBirthday(new Date());
        user.setRegistration(user.getBirthday());

        userStorage.add(user);

        MemoryChatStorage memoryChatStorage = MemoryChatStorage.getInstance();

        Message message = new Message();
        message.setFrom("unknown");
        message.setSendDate(new Date());
        message.setText("Hello, admin");

        memoryChatStorage.addMessage(user.getLogin(), message);

        AboutStorage aboutStorage = AboutStorage.getInstance();

        About about = new About();
        String storage = sce.getServletContext().getInitParameter("storage");
        String path = sce.getServletContext().getInitParameter("storagePath");
        about.setStorage(storage);
        about.setTimeСreation(new Date());
        about.setPath(path);

        aboutStorage.add(about);

        StorageFactory factory = StorageFactory.getInstance();
        factory.setType(StorageType.valueOf(storage));
        IStorageService service = factory.typeStorageInstance();
        service.initData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String storage = sce.getServletContext().getInitParameter("storage");
        StorageFactory factory = StorageFactory.getInstance();
        factory.setType(StorageType.valueOf(storage));
        IStorageService service = factory.typeStorageInstance();
        service.saveData();
    }


}
