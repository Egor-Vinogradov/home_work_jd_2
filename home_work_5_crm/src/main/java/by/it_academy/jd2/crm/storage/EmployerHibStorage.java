package by.it_academy.jd2.crm.storage;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.EmployersHibernate;
import by.it_academy.jd2.crm.service.HibernateUtil;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import org.hibernate.Session;

import java.util.List;

public class EmployerHibStorage implements IEmployerStorage {
    private static EmployerHibStorage instance;
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public static EmployerHibStorage getInstance() {
        if (instance == null) {
            synchronized (EmployerHibStorage.class) {
                instance = new EmployerHibStorage();
            }
        }
        return instance;
    }

    @Override
    public void addEmployer(Employer employer) {
        EmployersHibernate employerHib = new EmployersHibernate();
        employerHib.setName(employer.getName());
        employerHib.setSalary(employer.getSalary());
//        employerHib.setPosition(new PositionHibernate(employer.getPosition()));
//        employerHib.setDepartmentsHibernate(new DepartmentsHibernate(employer.getDepartment()));

        this.session.beginTransaction();
        this.session.save(employerHib);
        this.session.getTransaction().commit();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int getCountEmployers() {
        return 0;
    }

    @Override
    public List<Employer> getAllEmployers() {
        return null;
    }

    @Override
    public Employer getEmployer(long id) {
        return null;
    }

    @Override
    public List<Employer> getEmployersOffLimit(int offset, int limit) {
        return null;
    }
}
