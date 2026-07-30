package nl.novi.eindopdracht.dtos.source;

public class SourceResponseDto {

    private Long id;

    private String name;

    private Long performerInstrumentId;

    private Long channelId;

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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}