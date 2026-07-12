package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
// I used the @UniqueConstraint because performer_id - act_id pairs should only be allowed to occur once
@Table(
        name = "performer_acts",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"performer_id", "act_id"}
        )
)

public class PerformerActEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "performer_id", nullable = false)
    private PerformerProfileEntity performerEntity;

    @ManyToOne
    @JoinColumn(name = "act_id", nullable = false)
    private ActEntity actEntity;

    private String role;

    // Getters and Setters

    public PerformerProfileEntity getPerformer() {
        return performerEntity;
    }

    public void setPerformer(PerformerProfileEntity performer) {
        this.performerEntity = performerEntity;
    }

    public ActEntity getAct() {
        return actEntity;
    }

    public void setAct(ActEntity actEntity) {
        this.actEntity = actEntity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PerformerProfileEntity getPerformerEntity() {
        return performerEntity;
    }

    public void setPerformerEntity(PerformerProfileEntity performerEntity) {
        this.performerEntity = performerEntity;
    }

    public ActEntity getActEntity() {
        return actEntity;
    }

    public void setActEntity(ActEntity actEntity) {
        this.actEntity = actEntity;
    }
}
