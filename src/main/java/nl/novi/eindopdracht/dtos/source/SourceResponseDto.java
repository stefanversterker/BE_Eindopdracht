package nl.novi.eindopdracht.dtos.source;

public class SourceResponseDto {

    private Long id;

    private String name;

    private Long performerInstrumentId;

    private Long microphoneId;

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
        this.performerInstrumentId = performerInstrumentId;
    }

    public Long getMicrophoneId() {
        return microphoneId;
    }

    public void setMicrophoneId(Long microphoneId) {
        this.microphoneId = microphoneId;
    }
}