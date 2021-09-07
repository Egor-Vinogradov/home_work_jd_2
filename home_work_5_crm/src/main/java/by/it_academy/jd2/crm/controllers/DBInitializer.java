package by.it_academy.jd2.crm.controllers;

import java.sql.*;

public class DBInitializer {

    private static final DBInitializer instance = new DBInitializer();

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER_MAME = "postgres";
    private static final String PASSWORD = "egor";
    private static final String URL = "jdbc:postgresql://localhost:5432/crm";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Ошибка загрузки драйвера БД", e);
        }
    }

    public static DBInitializer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER_MAME, PASSWORD);
        Statement statement = connection.createStatement()) {

            String name = "anton";
            Double salary = -431.0;

            /**
             * Добавление
             */
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO application.employers(\n" +
                    "\tname, salary)\n" +
                    "\tVALUES (?, ?);")) {
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);

                preparedStatement.executeUpdate();
            }

            /**
             * Получение
             */
            try (ResultSet resultSet = statement.executeQuery("SELECT id, name, salary FROM application.employers ORDER BY id ASC ")) {
                System.out.printf("id\tИмя\tЗарплата\n");
                while (resultSet.next()) {
                    System.out.printf("%d\t%s\t%,.2f\n",
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3)
                    );
                }
            }


        } catch (Exception e) {
            throw new IllegalStateException("Ошибка работы с БД", e);
        }
    }
}
