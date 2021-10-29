package by.it_academy.jd2.food_control.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "application")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Brand brand;

    private double calories;
    private double protein;
    private double fats;
    private double carbohydrates;
    private double measure;

    public Product() {
    }

    public Product(Long id, Brand brand, double calories, double protein, double fats, double carbohydrates, double measure) {
        this.id = id;
        this.brand = brand;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.measure = measure;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", measure=" + measure +
                '}';
    }
}
