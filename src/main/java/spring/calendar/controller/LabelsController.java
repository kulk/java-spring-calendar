package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.calendar.model.Label;
import spring.calendar.model.User;
import spring.calendar.model.service.LabelService;
import spring.calendar.model.service.UserService;

import java.util.List;

@Controller
public class LabelsController {

    @Autowired
    LabelService labelService;

    @Autowired
    UserService userService;

    @GetMapping("labels")
    public String labelsHandler(Model model){
        User user = userService.getUser();
        List<Label> labels =  user.getLabels();
        model.addAttribute("labels", labels);
        return "labels";
    }

    @PostMapping("create_label")
    public String createLabel(@ModelAttribute(name="label") Label label){
        User user = userService.getUser();
        user.addLabel(label);
        userService.save(user);
        return "redirect:/labels";
    }
}
