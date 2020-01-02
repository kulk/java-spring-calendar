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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


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
        Event event1 = new Event("Mike's birthday", LocalDate.now(), labels.get(1));
        user1.addEvent(event1);
        userDao.save(user1);
        userDao.save(user2);
        eventDao.save(event1);
    }
    //Todo: Hier verder: user_events tabel is leeg

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
}
