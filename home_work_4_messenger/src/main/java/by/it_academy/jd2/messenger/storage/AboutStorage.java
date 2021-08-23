package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.About;
import by.it_academy.jd2.messenger.model.User;
import by.it_academy.jd2.messenger.storage.api.IAboutStorage;

public class AboutStorage implements IAboutStorage {

    private static final AboutStorage instance = new AboutStorage();

    public static AboutStorage getInstance() {
        return instance;
    }

    private About about = new About();

    @Override
    public void add(About about) {
        this.about = about;
    }

    @Override
    public About getAbout() {
        return this.about;
    }
}
