package by.it_academy.jd2.food_control.service.api;

public interface ILoginSearch<T> {
    T findByLogin(String login);
}
