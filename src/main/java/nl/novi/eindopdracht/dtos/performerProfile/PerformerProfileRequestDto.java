package nl.novi.eindopdracht.dtos.performerProfile;

import jakarta.validation.constraints.NotNull;

public class PerformerProfileRequestDto {

    @NotNull(message = "Instrument ID is required.")
    private Long instrumentId;

    @NotNull(message = "Person ID is required.")
    private Long personId;

    // Getters and Setters

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;


    }
}
