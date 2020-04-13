package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring.calendar.model.service.TestDbService;


@Controller
public class TestDbController {

    @Autowired
    TestDbService testDbService;

    @GetMapping("create-test-db")
    public String fillDbLauncher(){
        testDbService.fillDb();
        return "redirect:/";
    }



}
