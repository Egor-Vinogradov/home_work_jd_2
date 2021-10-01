package by.it_academy.jd2.crm.model.hibernate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "position", schema = "application_hib")
public class PositionHibernate implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public PositionHibernate() {
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
        return "PositionHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
