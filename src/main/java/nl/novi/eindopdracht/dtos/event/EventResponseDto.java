package nl.novi.eindopdracht.dtos.event;

import java.time.LocalDate;
import java.util.Date;

public class EventResponseDto {

    private Long id;

    private LocalDate date;

    private String venue;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

}
