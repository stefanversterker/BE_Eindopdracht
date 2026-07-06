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

public class PerformerAct extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private PerformerProfile performer;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Act act;

    private String role;

    public PerformerProfile getPerformer() {
        return performer;
    }

    public void setPerformer(PerformerProfile performer) {
        this.performer = performer;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
