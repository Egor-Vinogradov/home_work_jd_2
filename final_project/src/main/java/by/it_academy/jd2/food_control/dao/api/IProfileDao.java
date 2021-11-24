package by.it_academy.jd2.food_control.dao.api;

import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfileDao extends JpaRepository<Profile, Long> {

    List<Profile> findByUser(User user);
}
