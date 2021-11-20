package by.it_academy.jd2.food_control.model.api;

public enum ERoleUser {
    ROLE_USER ("ROLE_USER"),
    ROLE_ADMIN ("ROLE_ADMIN");

    private String title;

    ERoleUser(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
