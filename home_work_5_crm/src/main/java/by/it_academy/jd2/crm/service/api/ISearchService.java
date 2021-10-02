package by.it_academy.jd2.crm.service.api;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.filter.EmployeeSearchFilter;

import java.util.List;

public interface ISearchService {
    List<Employer> getEmployersSearch(int offset, int limit, String name, double from, double to);
    List<Employer> getEmployersSearch(EmployeeSearchFilter searchFilter);
}
