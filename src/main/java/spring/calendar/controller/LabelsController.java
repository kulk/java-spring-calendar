package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.calendar.model.Label;
import spring.calendar.model.service.LabelService;
import spring.calendar.model.service.UserService;

@Controller
public class LabelsController {

    @Autowired
    LabelService labelService;

    @Autowired
    UserService userService;

    @GetMapping("labels")
    public String labelsHandler(Model model) {
        model.addAttribute("labels", labelService.getLabelsByUser());
        return "labels";
    }

    @PostMapping("create_label")
    public String createLabel(@ModelAttribute(name = "label") Label label) {
        labelService.createNewLabel(label);
        return "redirect:/labels";
    }
}
