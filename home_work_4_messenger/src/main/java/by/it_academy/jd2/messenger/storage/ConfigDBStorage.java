package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.ConfigDB;

public class ConfigDBStorage {
    private static final ConfigDBStorage instance = new ConfigDBStorage();

    private ConfigDB configDB = new ConfigDB();

    public void add(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public ConfigDB getConfigDB() {
        return this.configDB;
    }

    public static ConfigDBStorage getInstance() {
        return instance;
    }
}
