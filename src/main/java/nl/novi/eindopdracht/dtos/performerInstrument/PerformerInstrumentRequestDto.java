package nl.novi.eindopdracht.dtos.performerInstrument;

import jakarta.validation.constraints.NotNull;

public class PerformerInstrumentRequestDto {

    @NotNull(message = "PerformerProfile ID is required.")
    private Long performerProfileId;

    @NotNull(message = "Instrument ID is required.")
    private Long instrumentId;

    // Getters and Setters

    public Long getPerformerProfileId() {
        return performerProfileId;
    }

    public void setPerformerProfileId(Long performerProfileId) {
        this.performerProfileId = performerProfileId;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }
}
