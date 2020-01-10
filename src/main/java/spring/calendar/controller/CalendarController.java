package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.User;
import spring.calendar.model.service.LoginService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    LoginService loginService;


    @GetMapping("calendar")
    public String calendarLauncher(@SessionAttribute("user") User user, Model model){
        List<Event> events = user.getEvents();

        HashMap<String, ArrayList<Event>> eventMap = createEventMap(events);
        model.addAttribute("testEvents", eventMap);

        return "calendar";
    }

    public HashMap<String, ArrayList<Event>> createEventMap(List<Event> events){
        HashMap<String, ArrayList<Event>> eventMap = new HashMap<>();
        String month = "";
//        * - Loop trough events List
        for(Event event : events){
//        * - Look up month of event
            month = extractKey(event);
            if(eventMap.containsKey(month)){
                ArrayList<Event> eventList = eventMap.get(month);
                eventList.add(event);
                eventMap.put(month, eventList);
            } else {
                ArrayList<Event> eventList = new ArrayList<>();
                eventList.add(event);
                eventMap.put(month, eventList);
            }
        }
//        * - Create key String if needed
//        * - Create value List if needed
//        * - Add Event to the value List
        return  eventMap;
    }
    /*public ArrayList<Event> addEventToList(ArrayList<Event> eventList){

    }*/

    public String extractKey(Event event){
        LocalDate date = event.getDate();
        String stringDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String month = String.valueOf(stringDate.charAt(3)) + stringDate.charAt(4);
        return month;
    }
}
