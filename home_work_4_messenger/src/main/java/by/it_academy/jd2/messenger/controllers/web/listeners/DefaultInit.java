package by.it_academy.jd2.messenger.controllers.web.listeners;

import by.it_academy.jd2.messenger.model.About;
import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.*;
import by.it_academy.jd2.messenger.view.api.IMessageService;
import by.it_academy.jd2.messenger.view.api.IUserService;
import by.it_academy.jd2.messenger.view.init_service.SavingRestoringDataFile;
import by.it_academy.jd2.messenger.view.api.IStorageService;
import by.it_academy.jd2.messenger.view.service.MessageService;
import by.it_academy.jd2.messenger.view.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class DefaultInit extends SavingRestoringDataFile implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setFio("admin");
        user.setBirthday(new Date());
        user.setRegistration(user.getBirthday());

        Message message = new Message();
        message.setFrom(user.getLogin());
        message.setSendDate(new Date());
        message.setText("Hello, admin");

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

        IUserService userService = UserService.getInstance();
        userService.signUp(user);
        IMessageService messageService = MessageService.getInstance();
        messageService.addMessage(user.getLogin(), message);
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
