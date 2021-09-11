package by.it_academy.jd2.crm.storage;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.service.DBInitializer;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDepartmentStorage implements IPositionDepartmentStorage {
    private static PositionDepartmentStorage instance;
    private final Connection connection = DBInitializer.getInstance().getConnection();

    public static PositionDepartmentStorage getInstance() {
        if (instance == null) {
            synchronized (PositionDepartmentStorage.class) {
                instance = new PositionDepartmentStorage();
            }
        }
        return instance;
    }

    @Override
    public int getCount(String value) {
        int count = 0;

        String sqlText = "";

        if (value.equals("position")) {
            sqlText = "SELECT count(*)\n" +
                    "\tFROM application.positions;";
        } else if (value.equals("department")){
            sqlText = "SELECT count(*)\n" +
                    "\tFROM application.departments;";
        }


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
    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();

        String sqlText = "SELECT id, name, parent\n" +
                        "\tFROM application.departments;";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                long parent = rs.getLong(3);

                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setParent(parent);
                department.setParentName(getNameDepartment(parent));

                list.add(department);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return list;
    }

    @Override
    public String getNameDepartment(long id) {
        String name = "";
        String sqlText = "SELECT name\n" +
                "FROM application.departments\n" +
                "WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return name;
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();

        String sqlText = "SELECT id, name\n" +
                "\tFROM application.positions;";

        try (PreparedStatement statement = connection.prepareStatement(sqlText)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);

                Position position = new Position();
                position.setId(id);
                position.setName(name);

                list.add(position);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }

        return list;
    }
}
