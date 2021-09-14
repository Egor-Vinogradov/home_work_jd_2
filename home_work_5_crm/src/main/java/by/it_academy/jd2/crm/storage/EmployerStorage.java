package by.it_academy.jd2.crm.storage;

import by.it_academy.jd2.crm.service.DBInitializer;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployerStorage implements IEmployerStorage {
    private static EmployerStorage instance;
    private final Connection connection = DBInitializer.getInstance().getConnection();

    public static EmployerStorage getInstance() {
        if (instance == null) {
            synchronized (EmployerStorage.class) {
                instance = new EmployerStorage();
            }
        }
        return instance;
    }

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

    @Override
    public List<Employer> getAllEmployers() {
        List<Employer> list = new ArrayList<>();

        String sqlText = "SELECT em.id, em.name, em.salary, pos.name, dep.name\n" +
                "FROM application.employers as em left join application.positions as pos\n" +
                "on em.position = pos.id\n" +
                "left join application.departments as dep\n" +
                "on em.department = dep.id;";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                double salary = rs.getDouble(3);
                String namePosition = rs.getString(4);
                String nameDepartment = rs.getString(5);

                Employer employer = new Employer();
                employer.setId(id);
                employer.setName(name);
                employer.setSalary(salary);
                employer.setPositionName(namePosition);
                employer.setDepartmentName(nameDepartment);

                list.add(employer);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        Collections.sort(list, new Comparator<Employer>() {
            @Override
            public int compare(Employer o1, Employer o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });

        return list;
    }

    @Override
    public Employer getEmployer(long id) {
        Employer employer = new Employer();

        String sqlText = "SELECT em.id, em.name, em.salary, pos.name as position, dep.name as department\n" +
                "FROM application.employers as em left join application.positions as pos\n" +
                "on em.position = pos.id\n" +
                "left join application.departments as dep\n" +
                "on em.department = dep.id\n" +
                "WHERE em.id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                employer.setId(rs.getLong("id"));
                employer.setName(rs.getString("name"));
                employer.setSalary(rs.getDouble("salary"));
                employer.setPositionName(rs.getString("position"));
                employer.setDepartmentName(rs.getString("department"));
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return employer;
    }
}
