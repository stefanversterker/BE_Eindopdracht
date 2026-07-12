package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EventEntity extends BaseEntity {

    private LocalDate date;

    private String venue;

    @OneToMany(mappedBy = "eventEntity")
    private List<PerformanceEntity> performances = new ArrayList<>();

    @OneToMany(mappedBy = "eventEntity")
    private List<EventAssignmentEntity> eventAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "eventEntity")
    private List<EquipmentEventAssignmentEntity> equipmentEventAssignments = new ArrayList<>();

    //Getters and setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<PerformanceEntity> getPerformances() {
        return performances;
    }

    public void setPerformances(List<PerformanceEntity> performances) {
        this.performances = performances;
    }

    public List<EventAssignmentEntity> getEventAssignments() {
        return eventAssignments;
    }

    public void setEventAssignments(List<EventAssignmentEntity> eventAssignments) {
        this.eventAssignments = eventAssignments;
    }

    public List<EquipmentEventAssignmentEntity> getEquipmentEventAssignments() {
        return equipmentEventAssignments;
    }

    public void setEquipmentEventAssignments(List<EquipmentEventAssignmentEntity> equipmentEventAssignments) {
        this.equipmentEventAssignments = equipmentEventAssignments;
    }
}
