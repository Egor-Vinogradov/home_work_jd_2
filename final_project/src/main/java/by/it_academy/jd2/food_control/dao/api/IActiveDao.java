package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Active;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.Weighing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IActiveDao extends JpaRepository<Active, Long> {

    @Query("SELECT p FROM Active p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) ORDER BY p.id")
    List<Active> findByName(@Param("name") String name, Pageable pageable);

    Active findByProfileIdAndId(Long idProfile, Long id);

    List<Active> findAllByCreationDateBetweenAndProfile(LocalDateTime dateStart,
                                                          LocalDateTime dateEnd,
                                                          Profile profile,
                                                          Pageable pageable);

    List<Active> findAllByProfile(Profile profile, Pageable pageable);
}
