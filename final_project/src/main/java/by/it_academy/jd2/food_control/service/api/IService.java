package by.it_academy.jd2.food_control.service.api;

import by.it_academy.jd2.food_control.dto.search.SearchFilter;

import java.util.List;

public interface IService<T, Long> {
    Long addItem(T item);
    T findById(Long id);
    List<T> findAll(SearchFilter filter);
    boolean deleteId(Long id, Long version);
    T updateItem(Long id, T item);
}
