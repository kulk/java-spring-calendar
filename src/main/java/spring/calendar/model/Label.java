package spring.calendar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int labelId;
    private String labelName;
    @ManyToMany(mappedBy = "labels",cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    public Label() {
    }

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    //Getters & setters
    public int getLabelId() {
        return labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
