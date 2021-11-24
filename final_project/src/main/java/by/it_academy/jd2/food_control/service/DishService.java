package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IDishDao;
import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService implements IService<Dish, Long> {

    private final IDishDao repository;

    public DishService(IDishDao repository) {
        this.repository = repository;
    }

    @Override
    public Long addItem(Dish item) {
        item.setCreationDate(LocalDateTime.now());
        return this.repository.save(item).getId();
    }

    @Override
    public Dish findById(Long id) {
        try {
            return this.repository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Dish> findAll(SearchFilter filter) {
        int offset = Math.toIntExact(filter.getPage());
        int limit = Math.toIntExact(filter.getSize());

        try {
            return this.repository.findByName(filter.getName(), PageRequest.of(offset, limit));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        try {
            Dish dish = this.repository.findById(id).get();
//            dish.setVersion(version);
            this.repository.delete(dish);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Dish updateItem(Long id, Dish item, Long version) {
        try {
            item.setId(id);
            item.setUpdateDate(LocalDateTime.now());
            return this.repository.save(item);
        } catch (Exception e) {
            return null;
        }
    }

    /*
    временно для тестов
     */
    @PostConstruct
    private void startInit() {
        Dish dish = null;
        try {
            dish = findById(1L);
        } catch (Exception e) {
            try {
                dish = new Dish();
                dish.setName("dish1");
                addItem(dish);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Ошибка БД");
            }
        }
    }
}
