package by.it_academy.jd2.messenger.view.init_service;

import by.it_academy.jd2.messenger.model.About;
import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.AboutStorage;
import by.it_academy.jd2.messenger.storage.MemoryChatStorage;
import by.it_academy.jd2.messenger.storage.MemoryUserStorage;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.messenger.view.api.IMessageService;
import by.it_academy.jd2.messenger.view.api.IStorageService;
import by.it_academy.jd2.messenger.view.api.IUserService;
import by.it_academy.jd2.messenger.view.service.MessageService;
import by.it_academy.jd2.messenger.view.service.UserService;

import java.io.*;
import java.util.*;

public class SavingRestoringDataFile extends UserService implements IStorageService {
    private static final SavingRestoringDataFile instance = new SavingRestoringDataFile();

    private final AboutStorage aboutStorage;
    private final IChatStorage chatStorage;
    private final IUserStorage userStorage;
    private IMessageService messageService;
    private IUserService userService;

    private About about;
    private String pathFile;
    private File file;

    public SavingRestoringDataFile() {
        this.aboutStorage = AboutStorage.getInstance();
        this.chatStorage = MemoryChatStorage.getInstance();
        this.userStorage = MemoryUserStorage.getInstance();

        this.messageService = MessageService.getInstance();
        this.messageService.setChatStorage(this.chatStorage);
        this.userService = UserService.getInstance();
        this.userService.setUserStorage(this.userStorage);

        this.about = aboutStorage.getAbout();
    }

    public static SavingRestoringDataFile getInstance() {
        return instance;
    }

    @Override
    public void saveData() {
        Collection<User> userMap = this.userStorage.getAll();
        
        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter(file)) {
            for (User user : userMap) {
                writer.append("user")
                        .append(",")
                        .append(user.getLogin())
                        .append(",")
                        .append(user.getPassword())
                        .append(",")
                        .append(user.getFio())
                        .append(",")
                        .append((user.getBirthday() !=null)
                                ? Long.toString(user.getBirthday().getTime()) : "")
                        .append(",")
                        .append((user.getRegistration() != null)
                                ? Long.toString(user.getRegistration().getTime()) : "")
                        .append(eol);
                List<Message> messageList = this.chatStorage.get(user.getLogin());
                for (Message message : messageList) {
                    writer.append("message")
                            .append(",")
                            .append(user.getLogin())
                            .append(",")
                            .append((message.getFrom() != null)
                                    ? message.getFrom() : "")
                            .append(",")
                            .append((message.getText() != null)
                                    ? message.getText() : "")
                            .append(",")
                            .append((message.getSendDate() != null)
                                    ? Long.toString(message.getSendDate().getTime()) : "")
                            .append(eol);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void initData() {
        this.pathFile = this.about.getPath();
        this.file = new File(pathFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String nextLine = reader.readLine();

            while (nextLine != null) {
                String[] values_per_line = nextLine.split(",");
                switch (values_per_line[0]) {
                    case "user":
                        if (!values_per_line[1].equals("admin")) {
                            this.userStorage.add(createUser(values_per_line[1], values_per_line[2],
                                    values_per_line[3], values_per_line[4], values_per_line[5]));
                        }
                        break;
                    case "message":
                        this.chatStorage.addMessage(values_per_line[1], createMessage(values_per_line[2],
                                values_per_line[3], values_per_line[4]));
                        break;
                    default:
                        break;
                }
                nextLine = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User createUser(String login,
                            String password,
                            String fio,
                            String birthday,
                            String registration) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFio(fio);
        if (!birthday.equals("")) {
            user.setBirthday(new Date(Long.parseLong(birthday)));
        }
        if (!registration.equals("")) {
            user.setRegistration(new Date(Long.parseLong(registration)));
        }
        return user;
    }

    private Message createMessage(String from,
                                  String text,
                                  String sendDate) {
        Message message = new Message();
        if (!from.equals("")) {
            message.setFrom(from);
        }
        if (!message.equals("")) {
            message.setText(text);
        }
        if (!sendDate.equals("")) {
            message.setSendDate(new Date(Long.parseLong(sendDate)));
        }
        return message;
    }
}
