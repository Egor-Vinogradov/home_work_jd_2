package by.it_academy.jd2.crm_spring.crm_spring.controllers;

import by.it_academy.jd2.crm_spring.crm_spring.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller
public class StartController {

//    @CrossOrigin
    @RequestMapping(value = "/employee", method = RequestMethod.GET, consumes = "application/json")
//    public Employee getName(@RequestParam(value = "name", required = false, defaultValue = "Egor") String name) {
    public List<Employee> getName(@RequestBody Employee employee) {
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        for (int i = 1; i < 5; i++) {
            Employee employee1 = new Employee(i, employee.getName());
            list.add(employee1);
        }
        return list;
    }

    @RequestMapping(value = "/123", method = RequestMethod.GET)
    public String getName() {
        return "ljkhlkjh";
    }
}
