package by.it_academy.jd2.crm.service.api;

import by.it_academy.jd2.crm.model.Employer;

import java.util.List;

public interface IEmployersService {
    void add(Employer employer);
    void deleteAll();
    void generateEmployers(int number);
    int getCountEmployers();
    List<Employer> getAllEmployers();
    Employer getEmployer(long id);
    List<Employer> getEmployersOffLimit(int offset, int limit);

}
