package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring.calendar.model.Dao.EventDao;
import spring.calendar.model.Dao.LabelDao;
import spring.calendar.model.Dao.UserDao;
import spring.calendar.model.Event;
import spring.calendar.model.Label;
import spring.calendar.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


@Controller
public class FillDbController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private LabelDao labelDao;

    @GetMapping("fdb")
    public String fillDbLauncher(){
        fillDb();
        return "login";
    }

    private void fillDb(){
        User user1 = new User("roeland@gmail.com", "123");
        User user2 = new User("mike@gmail.com", "123");
        ArrayList<Label> labels = createLabels();
        user1.setLabels(labels);
        Event event1 = new Event("Mike's birthday", LocalDate.now(), labels.get(1));
        user1.addEvent(event1);
        userDao.save(user1);
        userDao.save(user2);
        eventDao.save(event1);
        //loadTestEvents(user1);
    }

    private ArrayList<Label> createLabels(){
        ArrayList<Label> labels = new ArrayList<>();
        Collections.addAll(labels, new Label("Wedding"),
                new Label("Birthday"),
                new Label("Meeting"),
                new Label("Reminder"));
        for(Label label : labels){
            labelDao.save(label);
        }
        return labels;
    }

    private void loadTestEvents(User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            Scanner input = new Scanner(new File("../spring-calendar/src/main/resources/static/testdata/testevents.csv"));
            while(input.hasNextLine()){
                String row = input.nextLine();
                String[] rowArray = row.split(",");
                LocalDate date = LocalDate.parse(rowArray[0], formatter);
                Event event = new Event(rowArray[1], date);
                eventDao.save(event);
                user.addEvent(event);
            }
        } catch (FileNotFoundException notFound){
            System.out.println("File not found");
        }
    }

}
