package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserDao extends JpaRepository<User, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%') ORDER BY p.id")
    List<User> findByName(@Param("name") String name, Pageable pageable);
}
