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
    //public String calendarLauncher(@SessionAttribute("user") User user, Model model){
    public String calendarLauncher(Model model){
        User user = loginService.getUserByEmail("roeland@gmail.com");
        User user2 = loginService.getUserByEmail("mike@gmail.com");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        System.out.println(users.get(0).getEmail());
        System.out.println(users.get(1).getEmail());

        model.addAttribute("users", users);
        return "test";
    }
}
