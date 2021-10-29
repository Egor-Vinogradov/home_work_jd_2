package by.it_academy.jd2.food_control.service.api;

import by.it_academy.jd2.food_control.model.Brand;

import java.util.List;

public interface IBrandService {
    List<Brand> getAllBrands();
    Brand addBrand(Brand brand);
    Brand getBrand(Long id);
    Brand putBrand(Long id, Brand brand, long version);
    boolean deleteBrand(Long id, Long version);
}
