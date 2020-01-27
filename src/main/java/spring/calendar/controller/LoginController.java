package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import spring.calendar.model.User;
import spring.calendar.model.service.LoginService;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("login")
    public String loginHandler(){
        return "login";
    }

}
