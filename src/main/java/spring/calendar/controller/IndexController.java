package spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import spring.calendar.model.User;

@Controller
@SessionAttributes("user")
public class IndexController {

    @GetMapping("/")
    public String indexHandler(@SessionAttribute(required=false, name="user") User user){
        if(user == null){
            return "login";
        }
        return "redirect:/calendar";
    }

}
