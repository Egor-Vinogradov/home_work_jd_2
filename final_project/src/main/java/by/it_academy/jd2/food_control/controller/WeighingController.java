package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.service.WeighingJournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class WeighingController {

    private final WeighingJournalService service;

    public WeighingController(WeighingJournalService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addWeighing(@PathVariable("id_profile") Long id,
                                         @RequestBody WeighingActiveJournalDto weighing) {
        try {
            Weighing weighingNew = this.service.addItem(id, weighing);
            return new ResponseEntity<>(weighingNew, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPage(@PathVariable("id_profile") Long id) {
        SearchFilter filter = new SearchFilter();
        try {
            List<Weighing> weighings = this.service.getAllItems(id, filter);
            if (weighings != null) {
                return new ResponseEntity<>(weighings.size(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/", method = RequestMethod.GET)
    public ResponseEntity<?> getListWeighing(@PathVariable("id_profile") Long idProfile,
                                             @RequestParam(value = "page", required = false) Long page,
                                             @RequestParam(value = "size", required = false) Long size,
                                             @RequestParam(value = "dt_start", required = false) Long dataStart,
                                             @RequestParam(value = "dt_end", required = false) Long dataEnd) {
        SearchFilter filter = new SearchFilter();
        filter.setPage(page);
        filter.setSize(size);
        filter.setDataStart(dataStart);
        filter.setDataEnd(dataEnd);

        try {
            List<Weighing> weighings = this.service.getAllItems(idProfile, filter);
            if (weighings != null) {
                return new ResponseEntity<>(weighings, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/{id_weight}", method = RequestMethod.GET)
    public ResponseEntity<?> getWeighing(@PathVariable("id_profile") Long idProfile,
                                         @PathVariable("id_weight") Long idWeighing) {
        try {
            Weighing weighing = this.service.getItem(idProfile, idWeighing);
            if (weighing != null) {
                return new ResponseEntity<>(weighing, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateWeighing(@PathVariable("dt_update") Long version,
                                            @PathVariable("id_profile") Long idProfile,
                                            @PathVariable("id_weight") Long idWeight,
                                            @RequestBody Weighing weighing1) {
        try {
            Weighing weighing = this.service.updateItem(idProfile, idWeight,
                    version, weighing1);
            return new ResponseEntity<>(weighing, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWeighing(@PathVariable("id_profile") Long idProfile,
                                            @PathVariable("id_weight") Long idWeight,
                                            @PathVariable("dt_update") Long version) {
        if (this.service.deleteItem(idProfile, idWeight, version)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
