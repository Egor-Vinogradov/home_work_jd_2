package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.view.init_service.SavingRestoringDB;
import by.it_academy.jd2.messenger.view.init_service.SavingRestoringDataFile;
import by.it_academy.jd2.messenger.view.api.IStorageService;

public class StorageFactory extends SavingRestoringDataFile {
    private static final StorageFactory instance = new StorageFactory();
    private StorageType type;

    private StorageFactory() {
    }

    public void setType(StorageType type) {
        if (type != null) {
            this.type = type;
            typeStorageInstance();
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public IStorageService typeStorageInstance() {
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type) {
            case DB:
                return SavingRestoringDB.getInstance();
            case FILE:
                return SavingRestoringDataFile.getInstance();
            default:
                return null;
        }
    }

    public static StorageFactory getInstance() {
        return instance;
    }
}
