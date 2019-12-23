package spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("calendar")
    public String calendarLauncher(){
        return "calendar";
    }
}
