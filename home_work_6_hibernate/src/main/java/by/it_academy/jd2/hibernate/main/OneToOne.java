package by.it_academy.jd2.hibernate.main;

import by.it_academy.jd2.hibernate.model.oneToOne.DepartmentOneToOne;
import by.it_academy.jd2.hibernate.model.oneToOne.EmployerOneToOne;
import by.it_academy.jd2.hibernate.service.HibernateUtilOneToOne;
import org.hibernate.Session;

public class OneToOne {

    public static void main(String[] args) {
        Session session = HibernateUtilOneToOne.getSessionFactory().openSession();

        createDepartments(session);
        createEmployers(session);

        printEmployer(session, 1l);

        HibernateUtilOneToOne.shutdown();
    }

    public static void createDepartments(Session session) {
        session.beginTransaction();

        DepartmentOneToOne departmentOneToOne = new DepartmentOneToOne();
        departmentOneToOne.setName("One Department");

        DepartmentOneToOne departmentOneToOne1 = new DepartmentOneToOne();
        departmentOneToOne1.setName("Two Department");

        session.save(departmentOneToOne);
        session.save(departmentOneToOne1);

        session.getTransaction().commit();
    }

    public static void createEmployers(Session session) {
        session.beginTransaction();

        EmployerOneToOne employerOneToOne = new EmployerOneToOne();
        employerOneToOne.setName("One Employer");
        employerOneToOne.setDepartment(session.get(DepartmentOneToOne.class, 1l));

        EmployerOneToOne employerOneToOne1 = new EmployerOneToOne();
        employerOneToOne1.setName("Two Employer");
        employerOneToOne1.setDepartment(session.get(DepartmentOneToOne.class, 2l));

        session.save(employerOneToOne);
        session.save(employerOneToOne1);

        session.getTransaction().commit();
    }

    public static void printEmployer(Session session, long id) {
        EmployerOneToOne employerOneToOne = session.get(EmployerOneToOne.class, id);

        System.out.println(employerOneToOne);
    }
}
