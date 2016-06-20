package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.api.MailService;
import business.api.exceptions.AlreadyExistUserFieldException;
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
    
    private MailService mailService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }
    
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    public boolean registration(UserWrapper userWrapper) {
        if (null == userDao.findByUsernameOrEmail(userWrapper.getUsername())
                && null == userDao.findByUsernameOrEmail(userWrapper.getEmail())) {
            User user = new User(userWrapper.getUsername(), userWrapper.getSurname(),
            		userWrapper.getEmail(), userWrapper.getPhone(), userWrapper.getAssociation(),
            		userWrapper.getAddress(), userWrapper.getState(), userWrapper.getTown(),
            		userWrapper.getPostalCode(),userWrapper.getPassword());
            userDao.save(user);
            authorizationDao.save(new Authorization(user, Role.ASSOCIATION));
            
            mailService.sendMail("pfm.animals@gmail.com", user.getEmail(), "Bienvenido", "Bienvenido, " + user.getUsername() + ", has sido registrado en nuestro portal.");
            
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

	public void modifyAssocitaion(UserWrapper userWrapper, int id) throws AlreadyExistUserFieldException {
		User association = userDao.findUserById(id);
		if (association != null) {			
			if (userDao.findByUsernameOrEmail(userWrapper.getUsername()) != null && !association.getUsername().equalsIgnoreCase(userWrapper.getUsername())) {
				throw new AlreadyExistUserFieldException("Ya existe ese nombre de usuario");
			}			
			if (userDao.findByUsernameOrEmail(userWrapper.getEmail()) != null && !association.getEmail().equalsIgnoreCase(userWrapper.getEmail())) {
				throw new AlreadyExistUserFieldException("Ya existe ese email");
			}
			
			association.setUsername(userWrapper.getUsername());
			association.setSurname(userWrapper.getSurname());
			association.setEmail(userWrapper.getEmail());
			association.setPhone(userWrapper.getPhone());
			association.setAssociation(userWrapper.getAssociation());
			association.setAddress(userWrapper.getAddress());
			association.setState(userWrapper.getState());
			association.setTown(userWrapper.getTown());
			association.setPostalCode(userWrapper.getPostalCode());
			association.setPassword(userWrapper.getPassword());
			
			userDao.save(association);
		}
		
	}
}

