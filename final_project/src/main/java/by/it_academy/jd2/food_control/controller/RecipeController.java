package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.model.Recipe;
import by.it_academy.jd2.food_control.model.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin
public class RecipeController {

    private final IService<Dish, Long> dishIService;

    private final IService<Recipe, Long> recipeService;

    public RecipeController(IService<Dish, Long> dishIService,
                            IService<Recipe, Long> recipeService) {
        this.dishIService = dishIService;
        this.recipeService = recipeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Dish>> getAllDish(@RequestParam(value = "page", required = false) long page,
                                                 @RequestParam(value = "size", required = false) long size,
                                                 @RequestParam(value = "name", required = false) String name) {
        SearchFilter filter = new SearchFilter();
        filter.setName(name);
        filter.setSize(size);
        filter.setPage(page);

        List<Dish> dishes = this.dishIService.findAll(filter);
        if (dishes != null && dishes.size() > 0) {
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        Long idRecipe = this.recipeService.addItem(recipe);
        if (idRecipe != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRecipe(@PathVariable("id") Long id) {
        Recipe recipe = null;
        try {
            recipe = this.recipeService.findById(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe,
                                          @PathVariable("id") Long id,
                                          @PathVariable("dt_update") Long version) {
        recipe.setVersion(version);
        Recipe recipeUpdate = null;
        try {
            recipeUpdate = this.recipeService.updateItem(id, recipe);
            return new ResponseEntity<>(recipeUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id,
                                          @PathVariable("dt_update") Long version) {
        boolean result = this.recipeService.deleteId(id, version);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
