package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.search.ProductSearchFilter;
import by.it_academy.jd2.food_control.repository.ProductRepository;
import by.it_academy.jd2.food_control.service.api.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Iterable<Product> products = this.repository.findAll();
        for (Product product : products) {
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> getAllProducts(ProductSearchFilter filter) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        this.repository.save(product);
        return product;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = this.repository.findById(id).get();
        return product;
    }

    @Override
    public Product putProduct(Long id, Product product, Long version) {
//        this.repository.sa
        return null;
    }

    @Override
    public boolean deleteProduct(Long id, Long version) {
        this.repository.deleteById(id);
        return false;
    }
}
