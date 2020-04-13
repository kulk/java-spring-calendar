package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.calendar.model.User;
import spring.calendar.model.service.EventService;
import spring.calendar.model.service.LabelService;
import spring.calendar.model.service.UserService;


@Controller
public class CalendarController {

    @Autowired
    EventService eventService;

    @Autowired
    LabelService labelService;

    @Autowired
    UserService userService;

    @GetMapping("calendar")
    public String calendarHandler(Model model,
                                  @RequestParam (value = "error", required = false) String error) {
        User user = userService.getUser();
        model.addAttribute("events", eventService.getEventMap(user));
        model.addAttribute("labels", user.getLabels());
        model.addAttribute("backend_error", error);
        return "calendar";
    }

    @PostMapping("create_event")
    public String createEventHandler(@RequestParam(name = "event_name") String eventName,
                                     @RequestParam String date,
                                     @RequestParam String label) {
        String error = eventService.createNewEvent(eventName, date, label);
        return (error.equals("")) ? "redirect:/calendar" : "redirect:/calendar?error=" + error;
    }

    @GetMapping("delete_event")
    public String deleteEventHandler(@RequestParam(value = "id", required = false) int eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/calendar";
    }


}
