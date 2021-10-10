package by.it_academy.jd2.crm.storage.spring;

import by.it_academy.jd2.crm.model.Employer;
import by.it_academy.jd2.crm.model.filter.EPredicateOperator;
import by.it_academy.jd2.crm.model.filter.ESalaryOperator;
import by.it_academy.jd2.crm.model.filter.EmployeeSearchFilter;
import by.it_academy.jd2.crm.model.hibernate.DepartmentsHibernate;
import by.it_academy.jd2.crm.model.hibernate.EmployersHibernate;
import by.it_academy.jd2.crm.model.hibernate.PositionHibernate;
import by.it_academy.jd2.crm.storage.api.IEmployerStorage;
import by.it_academy.jd2.crm.storage.api.ISearchStorage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerHibStorageSpring implements IEmployerStorage, ISearchStorage {
    private SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder criteriaBuilder;

    public EmployerHibStorageSpring(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        this.criteriaBuilder = session.getCriteriaBuilder();
    }

    @Override
    public void addEmployer(Employer employer) {
        this.session.beginTransaction();

        EmployersHibernate employerHib = new EmployersHibernate();
        employerHib.setName(employer.getName());
        employerHib.setSalary(employer.getSalary());
        employerHib.setPosition(getListPositions(employer.getPosition()).get(0));
        employerHib.setDepartment(getListDepartments(employer.getDepartment()).get(0));


        this.session.save(employerHib);
        this.session.getTransaction().commit();
    }

    private List<PositionHibernate> getListPositions(long id) {
        CriteriaQuery<PositionHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                PositionHibernate.class);

        Root<PositionHibernate> itemRoot = criteriaQuery.from(PositionHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(itemRoot.get("id"), id)
        );

        List<PositionHibernate> position = session.createQuery(criteriaQuery).getResultList();

        return position;
    }

    private List<DepartmentsHibernate> getListDepartments(long id) {
        CriteriaQuery<DepartmentsHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                DepartmentsHibernate.class);

        Root<DepartmentsHibernate> itemRoot = criteriaQuery.from(DepartmentsHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(itemRoot.get("id"), id)
        );

        List<DepartmentsHibernate> departments = session.createQuery(criteriaQuery).getResultList();

        return departments;
    }

    @Override
    public void deleteAll() {
        this.session.beginTransaction();

        CriteriaDelete<EmployersHibernate> delete = criteriaBuilder.createCriteriaDelete(
                EmployersHibernate.class);

        delete.from(EmployersHibernate.class);

        this.session.createQuery(delete).executeUpdate();
        this.session.getTransaction().commit();
    }

    @Override
    public int getCountEmployers() {

        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(EmployersHibernate.class)));

        return session.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    @Override
    public List<Employer> getAllEmployers() {

        CriteriaQuery<EmployersHibernate> criteriaQuery = criteriaBuilder.createQuery(
                EmployersHibernate.class);

        List<EmployersHibernate> employersHibernates = session.createQuery(criteriaQuery).getResultList();

        List<Employer> employers = new ArrayList<>();

        for (EmployersHibernate employersHibernate : employersHibernates) {

            employers.add(adapterEmployer(employersHibernate));

        }
        return employers;
    }

    @Override
    public Employer getEmployer(long id) {
        this.session.beginTransaction();

        CriteriaQuery<EmployersHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                EmployersHibernate.class);

        Root<EmployersHibernate> itemRoot = criteriaQuery.from(EmployersHibernate.class);

        criteriaQuery.where(
                criteriaBuilder.equal(itemRoot.get("id"), id)
        );

        List<EmployersHibernate> employersHibernates = session.createQuery(criteriaQuery).getResultList();

        this.session.getTransaction().commit();

        return adapterEmployer(employersHibernates.get(0));
    }

    @Override
    public List<Employer> getEmployersOffLimit(int offset, int limit) {
        this.session.beginTransaction();

        CriteriaQuery<EmployersHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                EmployersHibernate.class);

        criteriaQuery.from(EmployersHibernate.class);

        List<EmployersHibernate> employersHibernates = session.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        this.session.getTransaction().commit();

        List<Employer> employers = new ArrayList<>();

        for (EmployersHibernate employersHibernate : employersHibernates) {

            employers.add(adapterEmployer(employersHibernate));

        }

        return employers;
    }

    private Employer adapterEmployer(EmployersHibernate employersHibernate) {
        Employer employer = new Employer();
        employer.setId(employersHibernate.getId());
        employer.setName(employersHibernate.getName());
        employer.setSalary(employersHibernate.getSalary());
        employer.setPosition(employersHibernate.getPosition().getId());
        employer.setDepartment(employersHibernate.getDepartment().getId());
        employer.setPositionName(employersHibernate.getPosition().getName());
        employer.setDepartmentName(employersHibernate.getDepartment().getName());

        return employer;
    }

    @Override
    public List<Employer> getEmployersSearch(int offset, int limit, String name, double from, double to) {
        this.session.beginTransaction();

        CriteriaQuery<EmployersHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                EmployersHibernate.class);

        Root<EmployersHibernate> itemRoot = criteriaQuery.from(EmployersHibernate.class);

        if (!name.equals("")) {
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(itemRoot.get("name"), name),
                            criteriaBuilder.between(itemRoot.get("salary"), from, to)
                    )
            );
        } else {
            criteriaQuery.where(
                    criteriaBuilder.between(itemRoot.get("salary"), from, to)
            );
        }

        List<EmployersHibernate> employersHibernates = session.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        this.session.getTransaction().commit();

        List<Employer> employers = new ArrayList<>();

        for (EmployersHibernate employersHibernate : employersHibernates) {

            employers.add(adapterEmployer(employersHibernate));

        }

        return employers;
    }

    @Override
    public List<Employer> getEmployersSearch(EmployeeSearchFilter filter) {
        this.session.beginTransaction();

        CriteriaQuery<EmployersHibernate> criteriaQuery = this.criteriaBuilder.createQuery(
                EmployersHibernate.class);

        Root<EmployersHibernate> itemRoot = criteriaQuery.from(EmployersHibernate.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(criteriaBuilder.equal(itemRoot.get("name"), filter.getName()));
        }

        if (filter.getSalary() != null) {
            ESalaryOperator operator = filter.getSalaryOperator();
            if (operator == null) {
                operator = ESalaryOperator.GREAT_OR_EQUAL;
            }
            Predicate predicate;
            switch (operator) {
                case GREAT_OR_EQUAL:
                    predicate = criteriaBuilder.ge(itemRoot.get("salary"), filter.getSalary());
                    break;
                case LESS_OR_EQUAL:
                    predicate = criteriaBuilder.le(itemRoot.get("salary"), filter.getSalary());
                default:
                    predicate = null;
            }

            if (predicate == null) {
                throw new IllegalArgumentException("Ошибка оператора");
            }

            predicates.add(predicate);
        }

        if (filter.getFrom() != null || filter.getTo() != null) {
            Predicate predicate = criteriaBuilder.between(itemRoot.get("salary"),
                    filter.getFrom(), filter.getTo());

            if (predicate == null) {
                throw  new IllegalArgumentException("Ошибка с интервалом");
            }

            predicates.add(predicate);
        }

        EPredicateOperator predicateOperator = filter.getPredicateOperator();

        if (predicateOperator == null) {
            predicateOperator = EPredicateOperator.AND;
        }

        Predicate[] predicatesArr = predicates.toArray(new Predicate[0]);

        Expression<Boolean> restriction;

        if (EPredicateOperator.AND.equals(predicateOperator)) {
            restriction = criteriaBuilder.and(predicatesArr);
        } else {
            restriction = criteriaBuilder.or(predicatesArr);
        }

        criteriaQuery.where(restriction);

        List<EmployersHibernate> employersHibernates = session.createQuery(criteriaQuery)
                .setFirstResult(filter.getOffset())
                .setMaxResults(filter.getLimit() == 0 ? 5 : filter.getLimit())
                .getResultList();

        session.getTransaction().commit();

        List<Employer> employers = new ArrayList<>();

        for (EmployersHibernate employersHibernate : employersHibernates) {

            employers.add(adapterEmployer(employersHibernate));

        }

        return employers;
    }
}
