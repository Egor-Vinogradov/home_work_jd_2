package by.it_academy.jd2.crm.storage;

import by.it_academy.jd2.crm.controllers.DBInitializer;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class EmployerStorage implements IEmployerStorage {
    private static EmployerStorage instance;

    public static EmployerStorage getInstance() {
        if (instance == null) {
            synchronized (EmployerStorage.class) {
                instance = new EmployerStorage();
            }
        }
        return instance;
    }

    private final Connection connection = DBInitializer.getInstance().getConnection();

    @Override
    public void addEmployer(Employer employer) {

        String name = employer.getName();
        double salary = employer.getSalary();
        long position = employer.getPosition();
        long department = employer.getDepartment();

        String sqlText = "INSERT INTO application.employers(\n" +
                        "\tname, salary, \"position\", department)\n" +
                        "\tVALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {

            statement.setString(1, name);
            statement.setDouble(2, salary);
            statement.setLong(3, position);
            statement.setLong(4, department);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
    }

    @Override
    public void deleteAll() {

        String sqlText = "DELETE FROM application.employers;";
        String sqlText2 = "ALTER SEQUENCE application.employers_id_seq RESTART WITH 1;";

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlText);
            statement.executeUpdate(sqlText2);

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
    }

    @Override
    public int getCountEmployers() {
        int count = 0;
        String sqlText = "SELECT count(*)\n" +
                        "\tFROM application.employers;";


        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
