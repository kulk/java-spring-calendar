package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
    public String labelsHandler(@SessionAttribute("user") User user,
                                Model model){
        List<Label> labels =  user.getLabels();
        model.addAttribute("labels", labels);
        return "labels";
    }

    @PostMapping("create_label")
    public String createLabel(@SessionAttribute("user") User user,
                              @RequestParam(name="label_name") String labelName){
        Label label = new Label(labelName);
        user.addLabel(label);
        userService.save(user);
        return "redirect:/labels";
    }
}
