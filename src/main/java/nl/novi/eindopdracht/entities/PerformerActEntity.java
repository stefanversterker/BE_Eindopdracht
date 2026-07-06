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
    @JoinColumn(name = "performer_id")
    private PerformerProfileEntity performer;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private ActEntity actEntity;

    //Getters and setters

    private String role;

    public PerformerProfileEntity getPerformer() {
        return performer;
    }

    public void setPerformer(PerformerProfileEntity performer) {
        this.performer = performer;
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


}
