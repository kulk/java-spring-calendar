package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Label;

@Service
public class LabelService {

    @Autowired
    private LabelService labelDao;

    public void save(Label label){
        labelDao.save(label);
    }
}
