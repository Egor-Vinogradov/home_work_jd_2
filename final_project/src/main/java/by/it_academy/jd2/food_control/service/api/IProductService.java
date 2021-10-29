package by.it_academy.jd2.food_control.service.api;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.search.ProductSearchFilter;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    List<Product> getAllProducts(ProductSearchFilter filter);
    Product addProduct(Product product);
    Product getProduct(Long id);
    Product putProduct(Long id, Product product, Long version);
    boolean deleteProduct(Long id, Long version);
}
