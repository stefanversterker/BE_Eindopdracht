package nl.novi.eindopdracht.dtos.performerInstrument;

public class PerformerInstrumentResponseDto {

    private Long id;

    private Long performerProfileId;

    private Long instrumentId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
