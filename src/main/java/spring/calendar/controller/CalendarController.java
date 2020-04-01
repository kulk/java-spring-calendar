package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.Label;
import spring.calendar.model.User;
import spring.calendar.model.service.EventService;
import spring.calendar.model.service.LabelService;
import spring.calendar.model.service.UserService;

import java.util.*;


@Controller
public class CalendarController {

    @Autowired
    EventService eventService;

    @Autowired
    LabelService labelService;

    @Autowired
    UserService userService;

    @GetMapping("calendar")
    public String calendarHandler(@SessionAttribute("user") User user, Model model){
        HashMap<String, ArrayList<Event>> eventMap = eventService.getEventMap(user);
        List<Label> labels = user.getLabels();
        model.addAttribute("testEvents", eventMap);
        model.addAttribute("labels", labels);
        return "calendar";
    }

    @PostMapping("create_event")
    public String createEventHandler(@SessionAttribute("user") User user,
                                     @RequestParam (name="event_name") String eventName,
                                     @RequestParam String date,
                                     @RequestParam String label){
        Event event = eventService.createEvent(eventName, date, label);
        user.addEvent(event);
        userService.save(user);
        return "redirect:/calendar";
    }

    @GetMapping("delete_event")
    public String deleteEventHandler(@SessionAttribute("user") User user,
                                     @RequestParam (value = "id", required = false) int eventId){
        // Remove event from User
        Event event = eventService.findEventByEventId(eventId);
        List<Event> events = user.getEvents();
        events.removeIf(n -> (n.getEventId() == eventId));
        userService.save(user);
        eventService.delete(event);
        return "redirect:/calendar";
    }




}
