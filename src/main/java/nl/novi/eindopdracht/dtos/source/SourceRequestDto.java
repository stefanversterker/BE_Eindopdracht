package nl.novi.eindopdracht.dtos.source;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SourceRequestDto {

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 100,
            message = "Name must be between 2 and 100 characters.")
    private String name;

    @NotNull(message = "PerformerInstrument ID is required.")
    private Long PerformerInstrumentId;


    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPerformerInstrumentId() {
        return PerformerInstrumentId;
    }

    public void setPerformerInstrumentId(Long performerInstrumentId) {
        PerformerInstrumentId = performerInstrumentId;
    }
}
