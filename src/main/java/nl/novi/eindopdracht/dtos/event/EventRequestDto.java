package nl.novi.eindopdracht.dtos.event;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class EventRequestDto {

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate date;

    @NotBlank(message = "Venue is required")
    private String venue;

    // Getters and Setters


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
