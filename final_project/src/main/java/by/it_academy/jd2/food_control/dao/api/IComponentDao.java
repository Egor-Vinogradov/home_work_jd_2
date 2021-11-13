package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComponentDao extends JpaRepository<Component, Long> {
}
