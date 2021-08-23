package by.it_academy.jd2.messenger.storage.api;

import by.it_academy.jd2.messenger.model.About;

public interface IAboutStorage {
    void add(About about);
    About getAbout();
}
