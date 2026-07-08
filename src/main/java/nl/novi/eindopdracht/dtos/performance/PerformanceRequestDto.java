package nl.novi.eindopdracht.dtos.performance;

import jakarta.validation.constraints.NotNull;

public class PerformanceRequestDto {

    @NotNull(message = "Event ID is required.")
    private Long eventId;

    @NotNull(message = "Act ID is required.")
    private Long actId;

    // Getters and Setters


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

}
