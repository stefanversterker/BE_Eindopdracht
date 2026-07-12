package nl.novi.eindopdracht.dtos.equipmentEventAssignment;

import jakarta.validation.constraints.NotNull;

public class EquipmentEventAssignmentRequestDto {

    @NotNull(message = "Equipment ID is required.")
    private Long equipmentId;

    @NotNull(message = "Event ID is required.")
    private Long eventId;

    // Getters and Setters

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
