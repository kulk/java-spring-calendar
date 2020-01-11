package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.calendar.model.Event;
import spring.calendar.model.Label;
import spring.calendar.model.User;
import spring.calendar.model.service.EventService;
import spring.calendar.model.service.LabelService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class CalendarController {

    @Autowired
    EventService eventService;

    @Autowired
    LabelService labelService;

    @GetMapping("calendar")
    public String calendarHandler(@SessionAttribute("user") User user, Model model){
        HashMap<String, ArrayList<Event>> eventMap = eventService.getEventMap(user);
        List<Label> labels = user.getLabels();
        /*for(Label l : labels){
            System.out.println(l.get);
        }*/
        model.addAttribute("testEvents", eventMap);
        model.addAttribute("labels", labels);
        return "calendar";
    }

    @PostMapping("create_event")
    public String createEventHandler(){
        return "redirect:/calendar";
    }


}
