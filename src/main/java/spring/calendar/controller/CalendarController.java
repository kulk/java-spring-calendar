package spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarController {

    @GetMapping("calendar")
    public String calendarLauncher(@SessionAttribute("user") User user, Model model){
        List<Event> events = user.getEvents();
        System.out.println("debug");
        for(Event event : events){
            System.out.println(event.getName()+"\n");

        }
        //model.addAttribute("events", user.getEvents());
        return "calendar";
    }
}
