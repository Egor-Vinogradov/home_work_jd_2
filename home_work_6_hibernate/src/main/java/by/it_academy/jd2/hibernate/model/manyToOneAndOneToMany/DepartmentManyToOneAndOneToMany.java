package by.it_academy.jd2.hibernate.model.manyToOneAndOneToMany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments", schema = "manytooneandonetomany")
public class DepartmentManyToOneAndOneToMany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn (name = "department")
    private List<EmployerManyToOneAndOneToMany> employers;

    public DepartmentManyToOneAndOneToMany() {
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

    public List<EmployerManyToOneAndOneToMany> getEmployer() {
        return employers;
    }

    public void setEmployer(List<EmployerManyToOneAndOneToMany> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "DepartmentManyToOneAndOneToMany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employer=" + employers +
                '}';
    }
}
