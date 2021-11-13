package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IRecipeDao;
import by.it_academy.jd2.food_control.model.*;
import by.it_academy.jd2.food_control.model.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService implements IService<Recipe, Long> {

    private final IRecipeDao repository;
    private final IService<Dish, Long> dishService;
    private final IService<User, Long> userService;
    private final IService<Product, Long> productService;
    private final IService<Component, Long> componentService;

    public RecipeService(IRecipeDao repository,
                         IService<Dish, Long> dishService,
                         IService<User, Long> userService,
                         IService<Product, Long> productService,
                         IService<Component, Long> componentService) {
        this.repository = repository;
        this.dishService = dishService;
        this.userService = userService;
        this.productService = productService;
        this.componentService = componentService;
    }

    @Override
    public Long addItem(Recipe item) {
        boolean verification = verificationRecipe(item);
        if (!verification) {
            Recipe recipe = clarificationRecipe(item);
            recipe.setCreationDate(LocalDateTime.now());
            return this.repository.save(recipe).getId();
        } else {
            return item.getId();
        }
    }

    /**
     * Метод для проверки на наличия блюда. Чтобы рецепты были уникальные для каждого блюда
     * @param recipe входящий рецепт с названием блюда
     * @return подтверждение наличия
     */
    private boolean verificationRecipe(Recipe recipe) {
        Dish dish = this.dishService.findById(recipe.getDish().getId());
        if (dish != null) {
            return true;
        }
        return false;
    }

    /**
     * Метод для уточнения входящего json. Если вдруг входящие данные не полные
     * @param entity входящие параметры
     * @return уточненный рецепт
     */
    private Recipe clarificationRecipe(Recipe entity) {
        Recipe recipe = new Recipe();

        Dish dish = this.dishService.findById(entity.getDish().getId());
        if (dish != null) {
            recipe.setDish(dish);
        } else {
            dish = new Dish();
            dish.setName(entity.getDish().getName());
            dish.setVersion(System.currentTimeMillis());
            Long idDish = this.dishService.addItem(dish);
            recipe.setDish(this.dishService.findById(idDish));
        }

        User user = this.userService.findById(entity.getUser().getId());
        recipe.setUser(user);

        List<Component> components = new ArrayList<>();

        for (Component component : entity.getComponents()) {
            Component componentNew = new Component();
            componentNew.setProduct(getProduct(component.getProduct().getId()));
            componentNew.setQuantity(component.getQuantity());
            Long idComponent = this.componentService.addItem(componentNew);
            components.add(this.componentService.findById(idComponent));
        }

        recipe.setComponents(components);

        return recipe;
    }

    private Product getProduct(Long idProduct) {
        return this.productService.findById(idProduct);
    }

    @Override
    public Recipe findById(Long id) {
        try {
            return this.repository.findById(id).get();
        } catch (Exception e) {
            throw new IllegalArgumentException("Рецепт по id не найден");
        }
    }

    /*
    пока не используется
     */
    @Override
    public List<Recipe> findAll(SearchFilter filter) {
        return null;
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        try {
            Recipe recipe = findById(id);
            recipe.setVersion(version);
            this.repository.delete(recipe);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Recipe updateItem(Long id, Recipe item) {
        Recipe recipe = null;
        try {
            recipe = this.repository.findById(id).get();
            recipe.setVersion(item.getVersion());
            recipe.setComponents(clarificationRecipe(item).getComponents());
            recipe.setUser(clarificationRecipe(item).getUser());
            recipe.setDish(clarificationRecipe(item).getDish());
            recipe.setUpdateDate(LocalDateTime.now());
            return this.repository.save(recipe);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось обновить продукт");
        }
    }
}
