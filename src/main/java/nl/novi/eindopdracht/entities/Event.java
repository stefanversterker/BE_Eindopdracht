package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends BaseEntity {

    private Date date;

    private String venue;

    @OneToMany(mappedBy = "event")
    private List<Performance> performances = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<EventAssignment> eventAssignments = new ArrayList<>();

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

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public List<EventAssignment> getEventAssignments() {
        return eventAssignments;
    }

    public void setEventAssignments(List<EventAssignment> eventAssignments) {
        this.eventAssignments = eventAssignments;
    }


}
