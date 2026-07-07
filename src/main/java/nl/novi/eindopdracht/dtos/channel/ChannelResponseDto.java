package nl.novi.eindopdracht.dtos.channel;

public class ChannelResponseDto {

    private Long id;

    private Integer number;

    private String label;

    private Long mixerId;

    private String sourceName;

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

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
