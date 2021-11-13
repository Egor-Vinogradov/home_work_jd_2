package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditDao extends JpaRepository<Audit, Long> {
}
