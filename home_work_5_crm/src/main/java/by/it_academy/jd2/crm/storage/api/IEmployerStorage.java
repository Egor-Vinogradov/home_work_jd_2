package by.it_academy.jd2.crm.storage.api;

import by.it_academy.jd2.crm.model.Employer;

public interface IEmployerStorage {
    void addEmployer(Employer employer);
    void deleteAll();
    int getCountEmployers();
}
