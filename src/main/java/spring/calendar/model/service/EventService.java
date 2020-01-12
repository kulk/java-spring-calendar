package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Dao.EventDao;
import spring.calendar.model.Dao.LabelDao;
import spring.calendar.model.Event;
import spring.calendar.model.Label;
import spring.calendar.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private LabelDao labelDao;

    public void save(Event event){
        eventDao.save(event);
    }

    public Event createEvent(String eventName, String date, String labelString){
        LocalDate eventDate = LocalDate.parse(date);
        Label label = labelDao.findLabelByLabelId(Integer.parseInt(labelString));
        Event event = new Event(eventName, eventDate, label);
        save(event);
        return event;
    }
    private String validateEventForm(){

        return "";
    }

    public HashMap<String, ArrayList<Event>> getEventMap(User user){
        List<Event> events = user.getEvents();
        HashMap<String, ArrayList<Event>> eventMap = new HashMap<>();
        for(Event event : events){
            eventMap = fillEventMap(eventMap, event);
        }
        return  eventMap;
    }

    private HashMap<String, ArrayList<Event>> fillEventMap(HashMap<String, ArrayList<Event>> eventMap, Event event){
        String month = extractKey(event);
        if(eventMap.containsKey(month)){
            ArrayList<Event> eventList = eventMap.get(month);
            eventList.add(event);
            eventMap.put(month, eventList);
        } else {
            ArrayList<Event> eventList = new ArrayList<>();
            eventList.add(event);
            eventMap.put(month, eventList);
        }
        return eventMap;
    }

    private String extractKey(Event event){
        LocalDate date = event.getEventDate();
        String stringDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.valueOf(stringDate.charAt(3)) + stringDate.charAt(4);
    }

}
