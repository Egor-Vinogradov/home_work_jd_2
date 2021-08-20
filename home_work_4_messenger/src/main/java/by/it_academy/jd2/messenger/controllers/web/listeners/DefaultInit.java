package by.it_academy.jd2.messenger.controllers.web.listeners;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.ChatStorage;
import by.it_academy.jd2.messenger.storage.UserStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class DefaultInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserStorage userStorage = UserStorage.getInstance();

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setFio("admin");
        user.setBirthday(new Date());
        user.setRegistration(user.getBirthday());

        userStorage.add(user);

        ChatStorage chatStorage = ChatStorage.getInstance();

        Message message = new Message();
        message.setFrom("unknown");
        message.setSendDate(new Date());
        message.setText("Hello, admin");

        chatStorage.addMessage(user.getLogin(), message);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
