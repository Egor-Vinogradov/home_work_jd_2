package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Audit;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin
public class AuditController {

    private final IService<Audit, Long> auditService;

    public AuditController(IService<Audit, Long> auditService) {
        this.auditService = auditService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Audit>> getListAudit(@RequestParam(value = "page", required = false) Long page,
                                                    @RequestParam(value = "size", required = false) Long size,
                                                    @RequestParam(value = "login", required = false) String login) {
        SearchFilter filter = new SearchFilter();
        filter.setSize(size);
        filter.setPage(page);
        filter.setLogin((login.equals("")) ? null : login);

        List<Audit> audits = this.auditService.findAll(filter);
        if (audits != null) {
            return new ResponseEntity<>(audits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // не по тз. для удобства фронта
    @RequestMapping(value = "/all/", method = RequestMethod.GET)
    public ResponseEntity<Long> getAllProductsPage (@RequestParam(value = "login", required = false) String login) {

        SearchFilter filter = new SearchFilter();
        filter.setLogin((login.equals("")) ? null : login);
        filter.setSize(null);
        filter.setPage(null);

        List<Audit> audits = this.auditService.findAll(filter);
        if (audits != null) {
            return new ResponseEntity<Long>(Long.valueOf(audits.size()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
