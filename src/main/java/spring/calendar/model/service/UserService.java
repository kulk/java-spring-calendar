package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Dao.UserDao;
import spring.calendar.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user){
        userDao.save(user);
    }
    public User findUserByEmail(String email){
        User user = userDao.findUserByEmail(email);
        return user;
    }



}
