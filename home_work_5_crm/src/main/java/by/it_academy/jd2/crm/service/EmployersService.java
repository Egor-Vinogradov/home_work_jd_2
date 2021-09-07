package by.it_academy.jd2.crm.service;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.service.api.IEmployersService;
import by.it_academy.jd2.crm.storage.EmployerStorage;
import by.it_academy.jd2.crm.storage.IEmployerStorage;

public class EmployersService implements IEmployersService {
    private static final EmployersService instance = new EmployersService();

    private final IEmployerStorage storage = EmployerStorage.getInstance();

    public static EmployersService getInstance() {
        return instance;
    }

    @Override
    public void add(Employer employer) {
        this.storage.addEmployer(employer);
    }
}
