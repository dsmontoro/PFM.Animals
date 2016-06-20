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
        User[] associations = this.createAssociations(0, 4);
        
        for (User association : associations) {
            map.put(association.getUsername(), association);
        }
               
        for (Token token : this.createTokens(associations)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
    }
        
    public User[] createAssociations(int initial, int size) {
        User[] associations = new User[size];
        for (int i = 0; i < size; i++) {
            associations[i] = new User("a" + (i + initial),"s" + (i + initial), "a" + (i + initial) + "@gmail.com", "000", "as" +(i + initial), "address" + (i + initial), "state" + (i + initial) , "town", "CP", "p");
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
