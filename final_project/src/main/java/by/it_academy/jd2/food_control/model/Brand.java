package by.it_academy.jd2.food_control.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brand", schema = "application")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
