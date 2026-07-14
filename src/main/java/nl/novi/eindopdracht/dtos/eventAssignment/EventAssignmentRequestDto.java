package nl.novi.eindopdracht.dtos.eventAssignment;

import jakarta.validation.constraints.NotNull;
import nl.novi.eindopdracht.enums.EventRole;

public class EventAssignmentRequestDto {

    @NotNull
    private Long eventId;

    @NotNull
    private Long personId;

    @NotNull
    private EventRole eventRole;

    // Getters and Setters


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public EventRole getEventRole() {
        return eventRole;
    }

    public void setEventRole(EventRole eventRole) {
        this.eventRole = eventRole;
    }
}
