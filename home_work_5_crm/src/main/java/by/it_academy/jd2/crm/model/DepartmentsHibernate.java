package by.it_academy.jd2.crm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments", schema = "application")
public class DepartmentsHibernate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "department")
    private List<EmployersHibernate> employers;

    public DepartmentsHibernate() {
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

    public List<EmployersHibernate> getEmployers() {
        return employers;
    }

    public void setEmployers(List<EmployersHibernate> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "DepartmentsHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
