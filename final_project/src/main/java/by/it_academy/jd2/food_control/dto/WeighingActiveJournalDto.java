package by.it_academy.jd2.food_control.dto;

import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.api.EEating;

import javax.persistence.OneToOne;

public class WeighingActiveJournalDto {
    private Long id;
    private Long creationDate;
    private Double weight;
    private Long version;
    private String name;
    private double calories;

    private EEating eating;
    private Profile profile;
    private Product product;
    private Dish dish;
    private double measure;

    public EEating getEating() {
        return eating;
    }

    public void setEating(EEating eating) {
        this.eating = eating;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
