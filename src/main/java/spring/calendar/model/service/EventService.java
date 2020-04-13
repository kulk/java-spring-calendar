package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Dao.EventDao;
import spring.calendar.model.Dao.LabelDao;
import spring.calendar.model.Event;
import spring.calendar.model.Label;
import spring.calendar.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EventService {

    @Autowired
    EventService eventService;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private LabelDao labelDao;

    @Autowired
    UserService userService;

    public void save(Event event){
        eventDao.save(event);
    }

    public Event findEventByEventId(int eventId){
        return eventDao.findEventByEventId(eventId);

    }

    public void deleteEvent(int eventId){
        User user = userService.getUser();
        Event event = findEventByEventId(eventId);
        List<Event> events = user.getEvents();
        events.removeIf(n -> (n.getEventId() == eventId));
        userService.save(user);
        eventDao.delete(event);
    }

    public void createNewEvent(String eventName, String date, String label){
        User user = userService.getUser();
        Event event = eventService.createEvent(eventName, date, label);
        user.addEvent(event);
        userService.save(user);
    }

    public Event createEvent(String eventName, String date, String labelString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime eventDate = LocalDateTime.parse(date + " 00:00", formatter);
        List<Label> labelList = parseLabelString(labelString);
        Event event = new Event(eventName, eventDate, labelList);
        save(event);
        return event;
    }

    private List<Label> parseLabelString(String labelString){
        List<Label> labelList = new ArrayList<>();
        String[] labelStringArray = labelString.split(",");
        for (int i = 0; i < labelStringArray.length; i++) {
            Label label = labelDao.findLabelByLabelId(Integer.parseInt(labelStringArray[i]));
            labelList.add(label);
        }
        return labelList;
    }

    public HashMap<String, ArrayList<Event>> getEventMap(User user){
        List<Event> events =  user.getEvents();
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
        LocalDateTime date = event.getEventDate();
        String stringDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.valueOf(stringDate.charAt(3)) + stringDate.charAt(4);
    }

}