package by.it_academy.jd2.food_control.model;

import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private EEssenceName essenceName;
    private Long essenceId;

    @Enumerated(EnumType.STRING)
    private ETypeAudit typeAudit;

    private LocalDateTime creationDate;

    @OneToOne
    private User user;

    public Audit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EEssenceName getEssenceName() {
        return essenceName;
    }

    public void setEssenceName(EEssenceName essenceName) {
        this.essenceName = essenceName;
    }

    public Long getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(Long essenceId) {
        this.essenceId = essenceId;
    }

    public ETypeAudit getTypeAudit() {
        return typeAudit;
    }

    public void setTypeAudit(ETypeAudit typeAudit) {
        this.typeAudit = typeAudit;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
