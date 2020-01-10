package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.User;
import spring.calendar.model.service.EventService;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class CalendarController {

    @Autowired
    EventService eventService;

    @GetMapping("calendar")
    public String calendarLauncher(@SessionAttribute("user") User user, Model model){
        HashMap<String, ArrayList<Event>> eventMap = eventService.createEventMap(user);
        model.addAttribute("testEvents", eventMap);
        return "calendar";
    }


}
