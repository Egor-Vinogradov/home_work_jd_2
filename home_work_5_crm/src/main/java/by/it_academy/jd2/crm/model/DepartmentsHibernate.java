package by.it_academy.jd2.crm.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments", schema = "application_hib")
public class DepartmentsHibernate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    @ManyToOne
    private DepartmentsHibernate parent;

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

    public DepartmentsHibernate getParent() {
        return parent;
    }

    public void setParent(DepartmentsHibernate parent) {
        this.parent = parent;
    }
}
