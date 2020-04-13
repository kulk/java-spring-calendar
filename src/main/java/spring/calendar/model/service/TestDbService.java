package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class TestDbService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private LabelDao labelDao;


    public void fillDb(){
        User user1 = new User("test@gmail.com", "123");
        User user2 = new User("mike@gmail.com", "123");
        ArrayList<Label> labels = createLabels();
        user1.setLabels(labels);
        Event event1 = new Event("Mike's birthday", LocalDate.now(), labels.get(1));
        user1.addEvent(event1);
        userDao.save(user1);
        userDao.save(user2);
        eventDao.save(event1);
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
        // Still in progress
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
