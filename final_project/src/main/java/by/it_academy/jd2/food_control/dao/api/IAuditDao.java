package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Audit;
import by.it_academy.jd2.food_control.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuditDao extends JpaRepository<Audit, Long> {

    List<Audit> findByUser(User user, Pageable pageable);
    List<Audit> findAllBy(Pageable pageable);
}
