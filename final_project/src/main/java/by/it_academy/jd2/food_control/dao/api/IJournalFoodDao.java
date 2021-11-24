package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.JournalFood;
import by.it_academy.jd2.food_control.model.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalFoodDao extends JpaRepository<JournalFood, Long> {

    List<JournalFood> findAllByProfile(Profile profile, Pageable pageable);

    List<JournalFood> findAllByCreationDateBetweenAndProfile(LocalDateTime dataStart,
                                                               LocalDateTime dataEnd,
                                                               Profile profile);

    JournalFood findByProfileIdAndId(Long idProfile, Long id);
}
