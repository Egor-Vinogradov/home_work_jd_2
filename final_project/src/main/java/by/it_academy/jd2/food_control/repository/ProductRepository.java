package by.it_academy.jd2.food_control.repository;

import by.it_academy.jd2.food_control.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
