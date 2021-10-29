package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.model.Brand;
import by.it_academy.jd2.food_control.repository.BrandRepository;
import by.it_academy.jd2.food_control.service.api.IBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    private BrandRepository repository;

    @Override
    public List<Brand> getAllBrands() {
        return null;
    }

    @Override
    public Brand addBrand(Brand brand) {
        return null;
    }

    @Override
    public Brand getBrand(Long id) {
        return null;
    }

    @Override
    public Brand putBrand(Long id, Brand brand, long version) {
        return null;
    }

    @Override
    public boolean deleteBrand(Long id, Long version) {
        return false;
    }
}
