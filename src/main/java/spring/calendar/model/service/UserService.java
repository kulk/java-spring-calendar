package spring.calendar.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findUserByEmail(userDetails.getUsername());
        return user;
    }



}
