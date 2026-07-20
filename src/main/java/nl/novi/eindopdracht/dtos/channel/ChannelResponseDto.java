package nl.novi.eindopdracht.dtos.channel;

public class ChannelResponseDto {

    private Long id;

    private Integer number;

    private String label;

    private Long mixerId;

    private Long sourceId;

    private String sourceName;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getMixerId() {
        return mixerId;
    }

    public void setMixerId(Long mixerId) {
        this.mixerId = mixerId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }


}
