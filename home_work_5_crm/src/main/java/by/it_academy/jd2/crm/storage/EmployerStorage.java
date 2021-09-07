package by.it_academy.jd2.crm.storage;

import by.it_academy.jd2.crm.model.Employer;

public class EmployerStorage implements IEmployerStorage {
    private static final EmployerStorage instance = new EmployerStorage();

    public static EmployerStorage getInstance() {
        return instance;
    }

    @Override
    public void addEmployer(Employer employer) {

    }
}
