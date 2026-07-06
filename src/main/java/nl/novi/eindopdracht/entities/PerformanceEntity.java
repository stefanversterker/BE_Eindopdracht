package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PerformanceEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private ActEntity actEntity;

    //Getters and setters

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
}
