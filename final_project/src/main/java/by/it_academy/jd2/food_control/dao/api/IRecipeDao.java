package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRecipeDao extends JpaRepository<Recipe, Long> {

    @Query("SELECT p FROM Recipe p ORDER BY p.id")
    List<Recipe> findBy(Pageable pageable);
}
