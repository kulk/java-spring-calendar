package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Dao.EventDao;
import spring.calendar.model.Event;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public void save(Event event){
        eventDao.save(event);
    }
}
