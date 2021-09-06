package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.messenger.view.DBInitializer;

import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DbUserStorage implements IUserStorage {
    private static final DbUserStorage instance = new DbUserStorage();

    private DBInitializer initializer = DBInitializer.getInstance();

    /**
     * Переменная для хранения коллекции юзеров. Ключ - логин
     */
    private final Map<String, User> users = new HashMap<>();

    private DbUserStorage() {
    }

    @Override
    public User get(String login) {
        User user = new User();
        Connection connection = this.initializer.getConnection();

        String sqlText = "SELECT login, password, fio, birthday, registration\n" +
                "\tFROM application.users\n" +
                "\tWHERE login = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlText)) {
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user.setLogin(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setFio(rs.getString(3));
                user.setBirthday(rs.getTime(4));
                user.setRegistration(rs.getTime(5));
            }

            rs.close();

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return user;
    }

    @Override
    public void add(User user) {
        Connection connection = this.initializer.getConnection();

        String login = user.getLogin();
        String password = user.getPassword();
        String fio = user.getFio();

        Date birthdayDate = user.getBirthday();
        java.sql.Date birthday = new java.sql.Date(birthdayDate.getTime());

        Date registrationDate = user.getRegistration();
        java.sql.Date registration = new java.sql.Date(registrationDate.getTime());

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO application.users(\n" +
                "\tlogin, password, fio, birthday, registration)\n" +
                "\tVALUES (?, ?, ?, ?, ?);")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fio);
            preparedStatement.setDate(4, birthday);
            preparedStatement.setDate(5, registration);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
    }

    @Override
    public Collection<User> getAll() {
        Connection connection = this.initializer.getConnection();

        String sqlText = "SELECT login, password, fio, birthday, registration\n" +
                "\tFROM application.users;";

        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sqlText)) {

            while (rs.next()) {
                User user = new User();

                user.setLogin(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setFio(rs.getString(3));
                user.setBirthday(rs.getTime(4));
                user.setRegistration(rs.getTime(5));

                this.users.put(user.getLogin(), user);
            }

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return this.users.values();
    }

    public static DbUserStorage getInstance() {
        return instance;
    }
}
