package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IProductDao;
import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IService<Product, Long> {

    private final IProductDao repository;
    private final UserService userService;

    public ProductService(IProductDao repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Long addItem(Product item) {
        item.setCreationDate(LocalDateTime.now());
        return this.repository.save(item).getId();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Продукт по id не найден")
        );
    }

    @Override
    public List<Product> findAll(SearchFilter filter) {
        int offset = (int) filter.getPage();
        int limit = (int) filter.getSize();

        try {
            return this.repository.findByName(filter.getName(), PageRequest.of(offset, limit));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        try {
            Product product = findById(id);
            product.setVersion(version);
            this.repository.delete(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product updateItem(Long id, Product item) {
        Product product = null;
        try {
            product = this.repository.findById(id).get();
            product.setName(item.getName());
            product.setBrand(item.getBrand());
            product.setCalories(item.getCalories());
            product.setProtein(item.getCalories());
            product.setProtein(item.getProtein());
            product.setFats(item.getFats());
            product.setCarbohydrates(item.getCarbohydrates());
            product.setMeasure(item.getMeasure());
            product.setUpdateDate(LocalDateTime.now());
            product.setVersion(item.getVersion());
            return this.repository.save(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось обновить продукт");
        }
    }

    @PostConstruct
    private void startInit() {
        for (int i = 1; i < 6; i++) {
            Product product = new Product();
            product.setName("Молоко " + i);
            product.setBrand("Савушкин");
            product.setMeasure(100);
            product.setUser(this.userService.findById(1L));
            addItem(product);
        }
        for (int i = 1; i < 6; i++) {
            Product product = new Product();
            product.setName("Хлеб " + i);
            product.setBrand("Перфект");
            product.setMeasure(100);
            product.setUser(this.userService.findById(1L));
            addItem(product);
        }
        for (int i = 1; i < 6; i++) {
            Product product = new Product();
            product.setName("Макароны " + i);
            product.setBrand("ХЗ");
            product.setMeasure(100);
            product.setUser(this.userService.findById(1L));
            addItem(product);
        }
    }
}
