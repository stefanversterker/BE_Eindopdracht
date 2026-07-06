package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EventEntity extends BaseEntity {

    private Date date;

    private String venue;

    @OneToMany(mappedBy = "eventEntity")
    private List<PerformanceEntity> performanceEntities = new ArrayList<>();

    @OneToMany(mappedBy = "eventEntity")
    private List<EventAssignmentEntity> eventAssignmentEntities = new ArrayList<>();

    //Getters and setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<PerformanceEntity> getPerformances() {
        return performanceEntities;
    }

    public void setPerformances(List<PerformanceEntity> performanceEntities) {
        this.performanceEntities = performanceEntities;
    }

    public List<EventAssignmentEntity> getEventAssignments() {
        return eventAssignmentEntities;
    }

    public void setEventAssignments(List<EventAssignmentEntity> eventAssignmentEntities) {
        this.eventAssignmentEntities = eventAssignmentEntities;
    }


}
