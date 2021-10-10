package by.it_academy.jd2.crm.model.hibernate;

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

    public PositionHibernate getPosition() {
        return position;
    }

    public void setPosition(PositionHibernate position) {
        this.position = position;
    }

    public DepartmentsHibernate getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentsHibernate department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployersHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position=" + position +
                ", department=" + department +
                '}';
    }
}
