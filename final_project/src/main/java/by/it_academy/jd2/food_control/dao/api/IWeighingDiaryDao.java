package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.Weighing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IWeighingDiaryDao extends JpaRepository<Weighing, Long> {

    List<Weighing> findAllByProfile(Profile profile, Pageable pageable);

    List<Weighing> findAllByCreationDateBetweenAndProfile(LocalDateTime dateStart,
                                                          LocalDateTime dateEnd,
                                                          Profile profile,
                                                          Pageable pageable);

    Weighing findByProfileIdAndId(Long idProfile, Long id);

    List<Weighing> findByCreationDateBeforeAndProfileId(LocalDateTime dateTime, Long id);
}
