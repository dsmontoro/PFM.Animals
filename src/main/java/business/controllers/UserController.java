package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.AssociationDetails;
import business.wrapper.AssociationState;
import business.wrapper.UserState;
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
            User user = new User(userWrapper.getUsername(), userWrapper.getEmail(), userWrapper.getPassword(), userWrapper.getConfirmedPassword(), userWrapper.getName(), userWrapper.getAddress(),userWrapper.getImgName());
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
    
    public List<UserState> showUsers() {
        List<UserState> userList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();
        usersList = (List<User>) userDao.findAllUsers();
        for (User user : usersList) {
            UserState userState = new UserState(user); 
            userList.add(userState);
        }
        return userList;
    }
    
    public List<AssociationState> showAssociations() {
        List<AssociationState> associationList = new ArrayList<>();
        for (User user : userDao.findAllAssociations()) {
            AssociationState userState = new AssociationState(user); 
            associationList.add(userState);
        }
        return associationList;
    }
    
    public AssociationDetails showAssociation(int id) {
        User association = userDao.findUserById(id);        
        
        return (new AssociationDetails(association));        
    }
    
    public List<AssociationState> searchAssociations(String name) {
        List<AssociationState> associations = new ArrayList<>();
        for (User association : userDao.searchAssociationsByName(name)) {
            AssociationState associationState = new AssociationState(association);
            associations.add(associationState);
        }
        
        return associations;
    }
}

