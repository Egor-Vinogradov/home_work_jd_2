package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IComponentDao;
import by.it_academy.jd2.food_control.model.Component;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComponentService implements IService<Component, Long> {

    private final IComponentDao repository;

    public ComponentService(IComponentDao repository) {
        this.repository = repository;
    }

    @Override
    public Long addItem(Component item) {
        item.setCreationDate(LocalDateTime.now());
        return this.repository.save(item).getId();
    }

    @Override
    public Component findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Компонент по id не найден")
        );
    }

    @Override
    public List<Component> findAll(SearchFilter filter) {
        return null;
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        return false;
    }

    @Override
    public Component updateItem(Long id, Component item) {
        item.setUpdateDate(LocalDateTime.now());
        return null;
    }
}
