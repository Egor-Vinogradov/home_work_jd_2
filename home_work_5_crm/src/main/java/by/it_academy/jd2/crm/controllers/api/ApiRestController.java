package by.it_academy.jd2.crm.controllers.api;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@RestController
//@RequestMapping("/api")
public class ApiRestController {

    private final IEmployersService employersService;
    private final IPositionDepartmentService positionDepartmentService;

    public ApiRestController(IEmployersService employersService,
                         IPositionDepartmentService positionDepartmentService) {
        this.employersService = employersService;
        this.positionDepartmentService = positionDepartmentService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public void pageNewEmployeer(HttpServletResponse resp, Model model) throws IOException {
        List<Department> departments = this.positionDepartmentService.getAllDepartments();
        List<Position> positions = this.positionDepartmentService.getAllPositions();
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        resp.sendRedirect("newemployeer");
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employer employer, Model model, HttpServletResponse resp) throws IOException {
        this.employersService.add(employer);
        resp.sendRedirect("employerslimit");
    }
}
