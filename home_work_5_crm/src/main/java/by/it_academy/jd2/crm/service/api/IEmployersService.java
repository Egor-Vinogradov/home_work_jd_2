package by.it_academy.jd2.crm.service.api;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Employer;

import java.util.List;
import java.util.Map;

public interface IEmployersService {
    void add(Employer employer);
    void deleteAll();
    void generateEmployers(int number);
    int getCountEmployers();
    List<Employer> getAllEmployers();

}
