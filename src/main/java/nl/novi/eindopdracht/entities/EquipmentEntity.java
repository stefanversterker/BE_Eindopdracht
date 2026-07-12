package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Here I use @Entity instead of @MappedSuperclass because I want to enable users to request an Equipment Table, so it's more than just a superclass.
@Entity
@Table(name = "equipment")
public abstract class EquipmentEntity extends BaseEntity {

    private String brand;

    private String model;

    @OneToMany(mappedBy = "equipmentEntity")
    private List<EquipmentEventAssignmentEntity> equipmentEventAssignments = new ArrayList<>();

    // Getters and Setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<EquipmentEventAssignmentEntity> getEquipmentEventAssignments() {
        return equipmentEventAssignments;
    }

    public void setEquipmentEventAssignments(List<EquipmentEventAssignmentEntity> equipmentEventAssignments) {
        this.equipmentEventAssignments = equipmentEventAssignments;
    }
}
