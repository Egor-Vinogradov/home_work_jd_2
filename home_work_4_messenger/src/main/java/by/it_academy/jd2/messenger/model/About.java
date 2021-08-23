package by.it_academy.jd2.messenger.model;

import java.util.Date;

public class About {
    private String storage;
    private Date timeСreation;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Date getTimeСreation() {
        return timeСreation;
    }

    public void setTimeСreation(Date timeСreation) {
        this.timeСreation = timeСreation;
    }
}
