package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.UserWrapper;
import data.daos.TokenDao;
import data.daos.UserDao;
import data.entities.Encrypt;
import data.entities.Token;
import data.entities.User;

@Controller
public class LogoutController {

private TokenDao tokenDao;
    
    private UserDao userDao;
    
    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public boolean logout(String tokenValue) {
        User user = userDao.findByTokenValue(tokenValue);
        assert user != null;
        Token token = tokenDao.findByUser(user);
        tokenDao.delete(token);
        return true;        
    }
}
