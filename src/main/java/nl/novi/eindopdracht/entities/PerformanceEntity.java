package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
// I used the @UniqueConstraint because an act should only be allowed to perform once at an event.
@Table(
        name = "performances",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"event_id", "act_id"}
        )
)

public class PerformanceEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private ActEntity actEntity;

    // Getters and Setters

    public EventEntity getEvent() {
        return eventEntity;
    }

    public void setEvent(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public ActEntity getAct() {
        return actEntity;
    }

    public void setAct(ActEntity actEntity) {
        this.actEntity = actEntity;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public ActEntity getActEntity() {
        return actEntity;
    }

    public void setActEntity(ActEntity actEntity) {
        this.actEntity = actEntity;
    }
}
