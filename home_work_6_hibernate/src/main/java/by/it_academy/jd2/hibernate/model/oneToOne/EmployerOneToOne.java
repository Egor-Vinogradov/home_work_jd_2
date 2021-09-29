package by.it_academy.jd2.hibernate.model.oneToOne;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employers", schema = "onetoone")
public class EmployerOneToOne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "department_id")
    private DepartmentOneToOne departmentOneToOne;

    public EmployerOneToOne() {
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

    public DepartmentOneToOne getDepartment() {
        return departmentOneToOne;
    }

    public void setDepartment(DepartmentOneToOne departmentOneToOne) {
        this.departmentOneToOne = departmentOneToOne;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + departmentOneToOne +
                '}';
    }
}
