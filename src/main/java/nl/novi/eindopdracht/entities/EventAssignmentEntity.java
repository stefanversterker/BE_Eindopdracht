package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;
import nl.novi.eindopdracht.enums.EventRole;

@Entity
public class EventAssignmentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @Enumerated(EnumType.STRING)
    private EventRole eventRole;

    //Getters and setters

    public EventEntity getEvent() {
        return eventEntity;
    }

    public void setEvent(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public PersonEntity getPerson() {
        return personEntity;
    }

    public void setPerson(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public EventRole getEventRole() {
        return eventRole;
    }

    public void setEventRole(EventRole eventRole) {
        this.eventRole = eventRole;
    }
}
