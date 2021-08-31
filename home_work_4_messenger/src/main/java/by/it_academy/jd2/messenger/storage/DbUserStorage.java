package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.ConfigDB;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.api.IUserStorage;

import java.sql.*;
import java.util.Collection;

public class DbUserStorage implements IUserStorage {
    private static final DbUserStorage instance = new DbUserStorage();

    private ConfigDBStorage configDBStorage = ConfigDBStorage.getInstance();

    private String user_name;
    private String password;
    private String url;
    private String driver;

    private DbUserStorage() {
    }

    @Override
    public User get(String login) {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    public static DbUserStorage getInstance() {
        return instance;
    }

    public void test() throws ClassNotFoundException {
        ConfigDB configDB = configDBStorage.getConfigDB();
        this.user_name = configDB.getUser_name();
        this.password = configDB.getPassword();
        this.url = configDB.getUrl();
        this.driver = configDB.getDriver();

        Class.forName(this.driver);
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user_name, this.password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select *\n" +
                    "from application.users");
            ResultSetMetaData meta = rs.getMetaData();

            int iColumnCount = meta.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= iColumnCount; i++) {
                    System.out.println(meta.getColumnName(i) + " : " + rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
