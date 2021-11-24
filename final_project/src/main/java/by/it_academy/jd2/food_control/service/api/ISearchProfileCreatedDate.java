package by.it_academy.jd2.food_control.service.api;

import java.time.LocalDateTime;

public interface ISearchProfileCreatedDate<T> {

    T findFirstCreationDate(Long idProfile, LocalDateTime dateTime);
}
