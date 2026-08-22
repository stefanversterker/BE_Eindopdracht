package nl.novi.eindopdracht.dtos.source;

public class SourceResponseDto {

    private Long id;

    private String name;

    private Long performerInstrumentId;

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
        return performerInstrumentId;
    }

    public void setPerformerInstrumentId(Long performerInstrumentId) {
        performerInstrumentId = performerInstrumentId;
    }
}