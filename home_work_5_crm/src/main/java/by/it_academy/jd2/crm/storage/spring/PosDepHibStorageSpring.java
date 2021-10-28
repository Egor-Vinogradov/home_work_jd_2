package by.it_academy.jd2.crm.storage.spring;

import by.it_academy.jd2.crm.model.Department;
import by.it_academy.jd2.crm.model.Position;
import by.it_academy.jd2.crm.model.hibernate.DepartmentsHibernate;
import by.it_academy.jd2.crm.model.hibernate.PositionHibernate;
import by.it_academy.jd2.crm.service.hibernate.HibernateUtil;
import by.it_academy.jd2.crm.storage.api.IPositionDepartmentStorage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PosDepHibStorageSpring implements IPositionDepartmentStorage {
    private SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder criteriaBuilder;

    public PosDepHibStorageSpring(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        this.criteriaBuilder = session.getCriteriaBuilder();
    }

    @Override
    public int getCount(String value) {
        int count = 0;

        if (value.equals("position")) {
            count = getCountPosition();
        } else if (value.equals("department")){
            count = getCountDepartments();
        }

        return count;
    }

    private int getCountPosition() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(PositionHibernate.class)));

        return session.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    private int getCountDepartments() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(DepartmentsHibernate.class)));

        return session.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    private Department adapterDepartment(DepartmentsHibernate departmentsHibernate) {
        Department department = new Department();
        department.setId(departmentsHibernate.getId());
        department.setName(departmentsHibernate.getName());

        if (departmentsHibernate.getParent() != null) {
            department.setParent(departmentsHibernate.getParent().getId());
            department.setParentName(departmentsHibernate.getParent().getName());
        } else {
            department.setParent(0);
            department.setParentName(null);
        }


        return department;
    }

    private Position adapterPosition(PositionHibernate positionHibernate) {
        Position position = new Position();
        position.setId(positionHibernate.getId());
        position.setName(positionHibernate.getName());

        return position;
    }

    @Override
    public List<Department> getAllDepartments() {

        CriteriaQuery<DepartmentsHibernate> criteriaQuery = criteriaBuilder.createQuery(
                DepartmentsHibernate.class);

        criteriaQuery.from(DepartmentsHibernate.class);

        List<DepartmentsHibernate> departmentsHibernates = session.createQuery(criteriaQuery)
                .getResultList();

        List<Department> departments = new ArrayList<>();

        for (DepartmentsHibernate departmentsHibernate : departmentsHibernates) {
            departments.add(adapterDepartment(departmentsHibernate));
        }

        return departments;
    }

    @Override
    public String getNameDepartment(long id) {

        CriteriaQuery<DepartmentsHibernate> criteriaQuery = criteriaBuilder.createQuery(
                DepartmentsHibernate.class);

        Root<DepartmentsHibernate> root = criteriaQuery.from(DepartmentsHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), id)
        );

        List<DepartmentsHibernate> departmentsHibernates = session.createQuery(criteriaQuery)
                .getResultList();

        return adapterDepartment(departmentsHibernates.get(0)).getName();
    }

    @Override
    public List<Position> getAllPositions() {

        CriteriaQuery<PositionHibernate> criteriaQuery = criteriaBuilder.createQuery(
                PositionHibernate.class);

        criteriaQuery.from(PositionHibernate.class);

        List<PositionHibernate> positionHibernates = session.createQuery(criteriaQuery)
                .getResultList();

        List<Position> positions = new ArrayList<>();

        for (PositionHibernate positionHibernate : positionHibernates) {
            positions.add(adapterPosition(positionHibernate));
        }

        return positions;
    }

    @Override
    public Position getPosition(long id) {

        CriteriaQuery<PositionHibernate> criteriaQuery = criteriaBuilder.createQuery(
                PositionHibernate.class);

        Root<PositionHibernate> root = criteriaQuery.from(PositionHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), id)
        );

        List<PositionHibernate> positionHibernates = session.createQuery(criteriaQuery).getResultList();

        return adapterPosition(positionHibernates.get(0));
    }

    @Override
    public Department getDepartment(long id) {

        CriteriaQuery<DepartmentsHibernate> criteriaQuery = criteriaBuilder.createQuery(
                DepartmentsHibernate.class);

        Root<DepartmentsHibernate> root = criteriaQuery.from(DepartmentsHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), id)
        );

        List<DepartmentsHibernate> departmentsHibernates = session.createQuery(criteriaQuery)
                .getResultList();

        return adapterDepartment(departmentsHibernates.get(0));
    }

    @Override
    public DepartmentsHibernate getDepartmentHibernate(long id) {
        CriteriaQuery<DepartmentsHibernate> criteriaQuery = criteriaBuilder.createQuery(
                DepartmentsHibernate.class);

        Root<DepartmentsHibernate> root = criteriaQuery.from(DepartmentsHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), id)
        );

        List<DepartmentsHibernate> departmentsHibernates = session.createQuery(criteriaQuery)
                .getResultList();
        return departmentsHibernates.get(0);
    }

    @Override
    public PositionHibernate getPositionHibernate(long id) {
        CriteriaQuery<PositionHibernate> criteriaQuery = criteriaBuilder.createQuery(
                PositionHibernate.class);

        Root<PositionHibernate> root = criteriaQuery.from(PositionHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(root.get("id"), id)
        );

        List<PositionHibernate> positionHibernates = session.createQuery(criteriaQuery).getResultList();
        return positionHibernates.get(0);
    }
}
