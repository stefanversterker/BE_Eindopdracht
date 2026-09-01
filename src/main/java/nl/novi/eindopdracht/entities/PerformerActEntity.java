package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
// I used the @UniqueConstraint because performer_id - act_id pairs should only be allowed to occur once
@Table(
        name = "performer_acts",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"performer_id", "act_id"}
        )
)

public class PerformerActEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "performer_id", nullable = false)
    private PerformerProfileEntity performerEntity;

    @ManyToOne
    @JoinColumn(name = "act_id", nullable = false)
    private ActEntity actEntity;

    @ElementCollection
    @CollectionTable(
            name = "performer_act_roles",
            joinColumns = @JoinColumn(name = "performer_act_id")
    )
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // Getters and Setters

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

