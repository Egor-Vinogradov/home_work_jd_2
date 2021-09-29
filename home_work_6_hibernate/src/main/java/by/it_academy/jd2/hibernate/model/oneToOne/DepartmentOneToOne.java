package by.it_academy.jd2.hibernate.model.oneToOne;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments", schema = "onetoone")
public class DepartmentOneToOne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public DepartmentOneToOne() {
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
