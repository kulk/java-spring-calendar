package spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("login")
    public String loginHandler(){
        return "login";
    }

    @PostMapping("do_login")
    public String doLoginHandler(@RequestParam(name = "username") String email,
                                 @RequestParam String password,
                                 Model model){
        System.out.println(email + "\n" + password);


        return "calendar";
    }

}
