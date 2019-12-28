package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.model.Dao.UserDao;
import spring.calendar.model.User;

@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    public boolean loginValid(String email, String password){
        User user = userDao.findUserByEmail(email);
        if(user != null){
            return user.getPassword().equals(password);
        }
        return false;
    }

    public User getUserByEmail(String email){
        User user = userDao.findUserByEmail(email);
        return user;
    }

}
