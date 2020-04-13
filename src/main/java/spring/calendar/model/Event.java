package spring.calendar.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int eventId;
    private String name;
    private LocalDateTime eventDate;
    @ManyToMany
    private List<Label> labels = new ArrayList<>();

    public Event() {
        super();
    }

    public Event(String name, LocalDateTime eventDate, Label label){
        this.name = name;
        this.eventDate = eventDate;
        this.labels.add(label);
    }

    public Event(String name, LocalDateTime eventDate, List<Label> labels){
        this.name = name;
        this.eventDate = eventDate;
        this.labels = labels;
    }

    public Event(String name, LocalDateTime eventDate){
        this.name = name;
        this.eventDate = eventDate;
    }

    public void addLabel(Label label){
        this.labels.add(label);
    }

    //Getters & setters
    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime date) {
        this.eventDate = date;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
