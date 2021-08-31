package by.it_academy.jd2.messenger.storage;

import by.it_academy.jd2.messenger.model.About;

public class AboutStorage {

    private static final AboutStorage instance = new AboutStorage();

    public static AboutStorage getInstance() {
        return instance;
    }

    private About about;

    public void add(About about) {
        this.about = about;
    }

    public About getAbout() {
        return this.about;
    }
}
