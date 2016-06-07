package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.UserWrapper;
import data.daos.AuthorizationDao;
import data.daos.UserDao;
import data.entities.Authorization;
import data.entities.Role;
import data.entities.User;

@Controller
public class UserController {

    private UserDao userDao;    

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }
    
    public boolean registration(UserWrapper userWrapper) {
        if (null == userDao.findByUsernameOrEmail(userWrapper.getUsername())
                && null == userDao.findByUsernameOrEmail(userWrapper.getEmail())) {
            User user = new User(userWrapper.getUsername(), userWrapper.getEmail(), userWrapper.getPassword(), userWrapper.getConfirmedPassword(), userWrapper.getName(), userWrapper.getAddress());
            userDao.save(user);
            if (userWrapper.getAddress() == null) {
                authorizationDao.save(new Authorization(user, Role.ADOPTER));
            }
            else {
                authorizationDao.save(new Authorization(user, Role.ASSOCIATION));
            }
            return true;
        } else {
            return false;
        }
    }
    
    public List<User> showUsers() {
        List<User> userList = new ArrayList<>();
        for (User user : userDao.findAll()) {
            userList.add(user);
        }
        return userList;
    }
}

