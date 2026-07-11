package nl.novi.eindopdracht.dtos.source;

public class SourceResponseDto {

    private Long id;

    private String name;

    private Long PerformerInstrumentId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
