package by.it_academy.jd2.hibernate.main;

import by.it_academy.jd2.hibernate.model.manyToOneAndOneToMany.DepartmentManyToOneAndOneToMany;
import by.it_academy.jd2.hibernate.model.manyToOneAndOneToMany.EmployerManyToOneAndOneToMany;
import by.it_academy.jd2.hibernate.service.HibernateUtilManyToOneAndOneToMany;
import org.hibernate.Session;
import java.util.List;

public class ManyToOneAndOneToMany {

    public static void main(String[] args) {
        Session session = HibernateUtilManyToOneAndOneToMany.getSessionFactory().openSession();

        createDepartments(session);
        createEmployers(session);

        printEmployers(session, 2);

        HibernateUtilManyToOneAndOneToMany.shutdown();

    }

    public static void createDepartments(Session session) {
        session.beginTransaction();

        DepartmentManyToOneAndOneToMany department = new DepartmentManyToOneAndOneToMany();
        department.setName("One Department");

        DepartmentManyToOneAndOneToMany department1 = new DepartmentManyToOneAndOneToMany();
        department1.setName("Two Department");

        session.save(department);
        session.save(department1);

        session.getTransaction().commit();
    }

    public static void createEmployers(Session session) {
        session.beginTransaction();

        EmployerManyToOneAndOneToMany employer = new EmployerManyToOneAndOneToMany();
        employer.setName("One Employer");
        employer.setDepartment(session.get(DepartmentManyToOneAndOneToMany.class, 1l));

        EmployerManyToOneAndOneToMany employer1 = new EmployerManyToOneAndOneToMany();
        employer1.setName("Two Employer");
        employer1.setDepartment(session.get(DepartmentManyToOneAndOneToMany.class,2l));

        EmployerManyToOneAndOneToMany employer2 = new EmployerManyToOneAndOneToMany();
        employer2.setName("Three Employer");
        employer2.setDepartment(session.get(DepartmentManyToOneAndOneToMany.class, 2l));

        session.save(employer);
        session.save(employer1);
        session.save(employer2);

        session.getTransaction().commit();
    }

    public static void printEmployers(Session session, long id) {
        session.beginTransaction();

        DepartmentManyToOneAndOneToMany department = session.get(DepartmentManyToOneAndOneToMany.class, id);

        System.out.println(session.contains(department));

        List<EmployerManyToOneAndOneToMany> list = department.getEmployer();

        for (EmployerManyToOneAndOneToMany employer : list) {
            System.out.println(employer.getName());
        }
    }
}
