package spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class IndexController {

    @GetMapping("/")
    public String indexHandler(){
        return "redirect:/calendar";
    }

}