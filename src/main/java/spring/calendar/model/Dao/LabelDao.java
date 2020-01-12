package spring.calendar.model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.calendar.model.Event;
import spring.calendar.model.Label;

@Repository
public interface LabelDao extends JpaRepository<Label, Integer> {

    Label findLabelByLabelId(int labelId);
}
