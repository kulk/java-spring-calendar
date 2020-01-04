package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.User;
import spring.calendar.model.service.LoginService;

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
        model.addAttribute("events", events);
        ArrayList<Event> testEvents = new ArrayList<>();
        testEvents.add(events.get(0));
        testEvents.add(events.get(1));
        testEvents.add(events.get(2));
        testEvents.add(events.get(3));
        HashMap<String, ArrayList<Event>> eventMap = createEventMap(testEvents);
        model.addAttribute("testEvents", eventMap);

        /*ArrayList<Event> testList01 = eventMap.get("01");
        for (Event x : testList01){
            System.out.println("DEBUG: " + x.getName());
        }*/
        /** Test with HashMpay**/
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("test1", "Ruben");
        testMap.put("test2", "Milena");
        model.addAttribute("testMap", testMap);
        /** End **/
        return "calendar";
    }

    public HashMap<String, ArrayList<Event>> createEventMap(ArrayList<Event> events){
        HashMap<String, ArrayList<Event>> eventMap = new HashMap<>();
        ArrayList<Event> events1 = new ArrayList<>();
        ArrayList<Event> events2 = new ArrayList<>();
        for (int i = 0; i < events.size() ; i++) {
            if(i % 2 == 0){
                events1.add(events.get(i));
            } else {
                System.out.println("DEBUG: events2.add()");
                events2.add(events.get(i));
            }
        }
        eventMap.put("01", events1);
        eventMap.put("02", events2);
        return  eventMap;
    }
}
