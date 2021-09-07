package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.Message;
import by.it_academy.jd2.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.messenger.view.DBInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbChatStorage implements IChatStorage {
    private static final DbChatStorage instance = new DbChatStorage();

    private DBInitializer initializer = DBInitializer.getInstance();

    public static DbChatStorage getInstance() {
        return instance;
    }


    @Override
    public List<Message> get(String login) {
        List<Message> list = new ArrayList<>();
        Connection connection = this.initializer.getConnection();

        String sqlText = "select login as from, message, registration\n" +
                "from application.messages ms\n" +
                "inner join (select id, login\n" +
                "    from application.users) us\n" +
                "    on ms.sender = us.id\n" +
                "where ms.recepient = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlText)) {
            preparedStatement.setLong(1, getUserNumber(login));

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message();

                message.setFrom(rs.getString(1));
                message.setText(rs.getString(2));
                message.setSendDate(rs.getDate(3));

                list.add(message);
            }

            rs.close();

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return list;
    }

    @Override
    public void addMessage(String login, Message message) {

        Connection connection = this.initializer.getConnection();
        String sqlText = "INSERT INTO application.messages(\n" +
                "\trecepient, sender, message, registration)\n" +
                "\tVALUES (?, ?, ?, ?);";

        String sender = message.getFrom();
        String text = message.getText();

        Date registrationDate = message.getSendDate();
        java.sql.Date registration = new java.sql.Date(registrationDate.getTime());

        try (PreparedStatement ps = connection.prepareStatement(sqlText)) {
            ps.setLong(1, getUserNumber(login));
            ps.setLong(2, getUserNumber(sender));
            ps.setString(3, text);
            ps.setDate(4, registration);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

    }

    private Long getUserNumber(String login) {
        Long number = null;
        Connection connection = this.initializer.getConnection();
        String sqlText = "SELECT id\n" +
                "\tFROM application.users\n" +
                "\tWHERE login = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlText)) {
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                number = rs.getLong(1);
            }

            rs.close();

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return number;
    }

    private String getNumberUser(Long number) {
        String userName = "";

        Connection connection = this.initializer.getConnection();
        String sqlText = "SELECT login\n" +
                "\tFROM application.users\n" +
                "\tWHERE id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlText)) {
            preparedStatement.setLong(1, number);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                userName = rs.getString(1);
            }

            rs.close();

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return userName;
    }
}
