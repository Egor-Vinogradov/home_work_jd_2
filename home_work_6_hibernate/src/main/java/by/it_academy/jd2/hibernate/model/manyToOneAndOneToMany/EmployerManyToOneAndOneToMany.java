package by.it_academy.jd2.hibernate.model.manyToOneAndOneToMany;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employers", schema = "manytooneandonetomany")
public class EmployerManyToOneAndOneToMany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private DepartmentManyToOneAndOneToMany department;

    public EmployerManyToOneAndOneToMany() {
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

    public DepartmentManyToOneAndOneToMany getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentManyToOneAndOneToMany department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployerManyToOneAndOneToMany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
