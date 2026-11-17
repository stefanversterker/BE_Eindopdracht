package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Column;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    private String fileName;

    private String contentType;

    @OneToMany(mappedBy = "image")
    private List<EquipmentEntity> equipmentItems = new ArrayList<>();

    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(name = "contents")
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
