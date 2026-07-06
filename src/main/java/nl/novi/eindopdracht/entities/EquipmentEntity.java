package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

//Here I use @Entity instead of @MappedSuperclass because I want to enable users to request an Equipment Table, so it's more than just a superclass.
@Entity
public abstract class EquipmentEntity extends BaseEntity {

    private String brand;

    private String model;

    //Getters and setters

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
}
