package by.it_academy.jd2.crm.controllers.api;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.model.hibernate.DepartmentsHibernate;
import by.it_academy.jd2.crm.model.hibernate.EmployersHibernate;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.service.api.IPositionDepartmentService;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtilAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/api")
public class ApiController {

    private final IEmployersService employersService;
    private final IPositionDepartmentService positionDepartmentService;

    public ApiController(IEmployersService employersService,
                         IPositionDepartmentService positionDepartmentService) {
        this.employersService = employersService;
        this.positionDepartmentService = positionDepartmentService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String pageNewEmployeer(HttpServletResponse resp, Model model) throws IOException {
        List<Department> departments = this.positionDepartmentService.getAllDepartments();
        List<Position> positions = this.positionDepartmentService.getAllPositions();
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        return "newemployeer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employer employer, Model model) {
        this.employersService.add(employer);
        return "employerslimit";
    }

}
