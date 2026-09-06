package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    private String fileName;

    private String contentType;

    @OneToMany(mappedBy = "image")
    private List<EquipmentEntity> equipmentItems = new ArrayList<>();

    @Lob
    private byte[] contents;

    // Getters and Setters

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<EquipmentEntity> getEquipment() {
        return equipmentItems;
    }

    public void setEquipment(List<EquipmentEntity> equipment) {
        this.equipmentItems = equipment;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
