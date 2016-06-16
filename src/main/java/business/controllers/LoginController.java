package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import business.wrapper.UserWrapper;
import data.daos.TokenDao;
import data.daos.UserDao;
import data.entities.Encrypt;
import data.entities.Token;
import data.entities.User;

@Controller
public class LoginController {

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
    
    public String login(UserWrapper userWrapper) {
        User user = userDao.findByUsernameOrEmail(userWrapper.getUsername());
        assert user != null;
        if (new BCryptPasswordEncoder().matches(userWrapper.getPassword(), user.getPassword())) {
            Token token = new Token(user);
            tokenDao.save(token);
            return token.getValue();
        }
        
        return "";
        
    }
}
