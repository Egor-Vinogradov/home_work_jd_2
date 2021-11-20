package by.it_academy.jd2.food_control.dto;

import by.it_academy.jd2.food_control.model.api.ERoleUser;
import by.it_academy.jd2.food_control.model.api.EUserStatus;

import javax.validation.constraints.Email;

public class LoginDto {

    @Email(message = "invalid format")
    private String login;
    private String password;
    private String token;
    private String name;
    private EUserStatus status;
    private ERoleUser role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EUserStatus getStatus() {
        return status;
    }

    public void setStatus(EUserStatus status) {
        this.status = status;
    }

    public ERoleUser getRole() {
        return role;
    }

    public void setRole(ERoleUser role) {
        this.role = role;
    }
}
