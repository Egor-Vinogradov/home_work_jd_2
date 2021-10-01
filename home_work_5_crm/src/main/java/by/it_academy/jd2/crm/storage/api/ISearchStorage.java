package by.it_academy.jd2.crm.storage.api;

import by.it_academy.jd2.crm.model.Employer;

import java.util.List;

public interface ISearchStorage {
    List<Employer> getEmployersSearch(int offset, int limit, String name, double from, double to);
}
