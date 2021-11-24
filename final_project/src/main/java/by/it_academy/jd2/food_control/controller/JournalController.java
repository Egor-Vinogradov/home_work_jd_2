package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.JournalFood;
import by.it_academy.jd2.food_control.service.JournalFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class JournalController {

    private final JournalFoodService service;

    public JournalController(JournalFoodService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id_profile}/journal/food/", method = RequestMethod.POST)
    public ResponseEntity<?> addJournalRecord(@PathVariable("id_profile") Long idProfile,
                                              @RequestBody WeighingActiveJournalDto record) {
        try {
            JournalFood journalFood = this.service.addItem(idProfile, record);
            return new ResponseEntity<>(journalFood, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/food/{id_food}", method = RequestMethod.GET)
    public ResponseEntity<?> getJournalRecord(@PathVariable("id_food") Long idFood,
                                              @PathVariable("id_profile") Long idProfile) {
        try {
            JournalFood journalFood = this.service.getItem(idProfile, idFood);
            if (journalFood != null) {
                return new ResponseEntity<>(journalFood, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/food/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRecords(@PathVariable("id_profile") Long idProfile,
                                           @RequestParam(value = "page", required = false) Long page,
                                           @RequestParam(value = "size", required = false) Long size) {

        SearchFilter filter = new SearchFilter();
        filter.setPage(page);
        filter.setSize(size);

        try {
            List<JournalFood> journalFoods = this.service.getAllItems(idProfile, filter);
            if (journalFoods != null) {
                return new ResponseEntity<>(journalFoods, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/food/byDay/{day}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRecordsDay(@PathVariable("id_profile") Long idProfile,
                                              @PathVariable("day") Long day) {
        SearchFilter filter = new SearchFilter();
        filter.setDay(day);

        try {
            List<JournalFood> journalFoods = this.service.getAllItems(idProfile, filter);
            if (journalFoods != null) {
                return new ResponseEntity<>(journalFoods, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateFood(@PathVariable("id_profile") Long idProfile,
                                          @PathVariable("id_food") Long idFood,
                                          @PathVariable("dt_update") Long version,
                                          @RequestBody JournalFood food) {
        try {
            JournalFood journalFood = this.service.updateItem(idProfile, idFood, version, food);
            return new ResponseEntity<>(journalFood, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFood(@PathVariable("id_profile") Long idProfile,
                                        @PathVariable("id_food") Long idWeight,
                                        @PathVariable("dt_update") Long version) {
        if (this.service.deleteItem(idProfile, idWeight, version)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
