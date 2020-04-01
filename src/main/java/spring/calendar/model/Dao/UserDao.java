package spring.calendar.model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.calendar.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
