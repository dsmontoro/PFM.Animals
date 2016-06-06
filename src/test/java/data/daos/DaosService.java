package data.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import data.entities.Authorization;
import data.entities.Role;
import data.entities.Token;
import data.entities.User;
import data.services.DataService;

@Service
public class DaosService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private DataService genericService;

    private Map<String, Object> map;

    @PostConstruct
    public void populate() {
        map = new HashMap<>();
        User[] users = this.createAdopters(0, 4);
        User[] associations = this.createAssociations(0, 4);
        for (User user : users) {
            map.put(user.getUsername(), user);
        }
        for (User association : associations) {
            map.put(association.getUsername(), association);
        }
        for (Token token : this.createTokens(users)) {
            map.put("t" + token.getUser().getUsername(), token);
        }        
        for (Token token : this.createTokens(associations)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
    }

    public User[] createAdopters(int initial, int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            users[i] = new User("u" + (i + initial), "u" + (i + initial) + "@gmail.com", "p", "p", "name" + (i + initial));
            userDao.save(users[i]);
            authorizationDao.save(new Authorization(users[i], Role.ADOPTER));
        }
        return users;
    }
    
    public User[] createAssociations(int initial, int size) {
        User[] associations = new User[size];
        for (int i = 0; i < size; i++) {
            associations[i] = new User("a" + (i + initial), "a" + (i + initial) + "@gmail.com", "p", "p", "name" + (i + initial), "address" + (i + initial));
            userDao.save(associations[i]);
            authorizationDao.save(new Authorization(associations[i], Role.ASSOCIATION));
        }
        return associations;
    }

    public List<Token> createTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        for (User user : users) {
            token = new Token(user);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void deleteAll() {
        genericService.deleteAllExceptAdmin();
    }
}
