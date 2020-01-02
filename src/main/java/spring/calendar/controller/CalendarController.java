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
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    LoginService loginService;


    @GetMapping("calendar")
    public String calendarLauncher(@SessionAttribute("user") User user, Model model){
        List<Event> events = user.getEvents();
        model.addAttribute("events", events);
        return "calendar";
    }
}
