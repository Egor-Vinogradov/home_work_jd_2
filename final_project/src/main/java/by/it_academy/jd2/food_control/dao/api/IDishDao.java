package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDishDao extends JpaRepository<Dish, Long> {

    @Query("SELECT p FROM Dish p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY p.id")
    List<Dish> findByName(@Param("name") String name, Pageable pageable);
}
