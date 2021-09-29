package by.it_academy.jd2.crm.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employers", schema = "application_hib")
public class EmployersHibernate implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double salary;

    @ManyToOne
    private PositionHibernate position;

    @ManyToOne
    private DepartmentsHibernate department;

    public EmployersHibernate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public DepartmentsHibernate getDepartmentsHibernate() {
        return department;
    }

    public void setDepartmentsHibernate(DepartmentsHibernate departmentsHibernate) {
        this.department = departmentsHibernate;
    }


}
