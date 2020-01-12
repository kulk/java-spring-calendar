package spring.calendar.model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.calendar.model.Event;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {

    /*void deleteByEventId(int eventId);*/

    Event findEventByEventId(int eventId);

}
