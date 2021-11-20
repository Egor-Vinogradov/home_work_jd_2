package by.it_academy.jd2.food_control.service.api;

import by.it_academy.jd2.food_control.dto.LoginDto;
import by.it_academy.jd2.food_control.model.User;

public interface IUserService extends IService<User, Long> {
    LoginDto authentication(LoginDto loginDto);
    LoginDto registration(LoginDto loginDto);
    LoginDto confirmationRegistration(String login);
}
