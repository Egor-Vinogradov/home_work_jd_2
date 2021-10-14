package by.it_academy.jd2.crm.service.hibernate;

import by.it_academy.jd2.crm.model.hibernate.DepartmentsHibernate;
import by.it_academy.jd2.crm.model.hibernate.PositionHibernate;
import by.it_academy.jd2.crm.service.spring.ApplicationContextUtilAnnotation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InitHibDB {

    private static void createPositions(Session session) {
        session.beginTransaction();

        PositionHibernate p = new PositionHibernate();
        p.setName("Директор");
        session.save(p);

        PositionHibernate p1 = new PositionHibernate();
        p1.setName("Главный бухгалтер");
        session.save(p1);

        PositionHibernate p2 = new PositionHibernate();
        p2.setName("Бухгалтер");
        session.save(p2);

        PositionHibernate p3 = new PositionHibernate();
        p3.setName("Экономист");
        session.save(p3);

        PositionHibernate p4 = new PositionHibernate();
        p4.setName("Специалист по снабжению");
        session.save(p4);

        PositionHibernate p5 = new PositionHibernate();
        p5.setName("Специалист по сбыту");
        session.save(p5);

        PositionHibernate p6 = new PositionHibernate();
        p6.setName("Программист");
        session.save(p6);

        PositionHibernate p7 = new PositionHibernate();
        p7.setName("Маркетолог");
        session.save(p7);

        PositionHibernate p8 = new PositionHibernate();
        p8.setName("Менчендайзер");
        session.save(p8);

        PositionHibernate p9 = new PositionHibernate();
        p9.setName("Курьер");
        session.save(p9);

        session.getTransaction().commit();
    }

    private static void createDepartments(Session session) {
        session.beginTransaction();

        DepartmentsHibernate d = new DepartmentsHibernate();
        d.setName("Администрация");
        session.save(d);

        DepartmentsHibernate d1 = new DepartmentsHibernate();
        d1.setName("Отдел товарооборота");
        d1.setParent(d);
        session.save(d1);

        DepartmentsHibernate d2 = new DepartmentsHibernate();
        d2.setName("Отдел продаж");
        d2.setParent(d1);
        session.save(d2);

        DepartmentsHibernate d3 = new DepartmentsHibernate();
        d3.setName("Отдел информационных технологий");
        d3.setParent(d);
        session.save(d3);

        DepartmentsHibernate d4 = new DepartmentsHibernate();
        d4.setName("Отдел продаж");
        d4.setParent(d1);
        session.save(d4);

        session.getTransaction().commit();
    }

    public  static void createTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        createPositions(session);
        createDepartments(session);
    }
}
