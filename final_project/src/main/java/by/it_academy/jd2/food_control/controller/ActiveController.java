package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Active;
import by.it_academy.jd2.food_control.service.ActiveJournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class ActiveController {

    private final ActiveJournalService service;

    public ActiveController(ActiveJournalService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id_profile}/journal/active/", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addActive(@PathVariable("id_profile") Long idProfile,
                                       @RequestBody WeighingActiveJournalDto activeDto) {
        try {
            Active active = this.service.addItem(idProfile, activeDto);
            return new ResponseEntity<>(active, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/active/{id_active}", method = RequestMethod.GET)
    public ResponseEntity<?> getActive(@PathVariable("id_profile") Long idProfile,
                                       @PathVariable("id_active") Long id) {
        try {
            Active active = this.service.getItem(idProfile, id);
            if (active != null) {
                return new ResponseEntity<>(active, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/active/{id_active}/dt_update/{dt_update}",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateActive(@PathVariable("id_profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long version,
                                          @RequestBody Active active) {
        try {
            Active updateActive = this.service.updateItem(idProfile, idActive, version, active);
            return new ResponseEntity<>(updateActive, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/active/{id_active}/dt_update/{dt_update}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteActive(@PathVariable("id_profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long version) {
        if (this.service.deleteItem(idProfile, idActive, version)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id_profile}/journal/active/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActives(@PathVariable("id_profile") Long idProfile,
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
            List<Active> actives = this.service.getAllItems(idProfile, filter);
            if (actives != null) {
                return new ResponseEntity<>(actives, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
