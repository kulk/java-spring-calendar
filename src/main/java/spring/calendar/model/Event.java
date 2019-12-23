package spring.calendar.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int eventId;
    private String name;
    private LocalDate date;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Label> labels = new ArrayList<>();

    public Event() {
        super();
    }

    public Event(String name, LocalDate date, Label label){
        this.name = name;
        this.date = date;
        this.labels.add(label);
    }

    public Event(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }

    public void addLabel(Label label){
        this.labels.add(label);
    }

    //Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
