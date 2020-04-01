package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Label;
import spring.calendar.model.User;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    UserService userService;

    public List<Label> getLabelsByUser(){
        User user = userService.getUser();
        return user.getLabels();
    }

    public void createNewLabel(Label label){
        User user = userService.getUser();
        user.addLabel(label);
        userService.save(user);
    }

}
