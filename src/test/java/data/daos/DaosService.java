package data.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entities.Animal;
import data.entities.Authorization;
import data.entities.Role;
import data.entities.Token;
import data.entities.Type;
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
    
    @Autowired
    private AnimalDao animalDao;
    
    @PostConstruct
    public void populate() {
        map = new HashMap<>();        
        User[] associations = this.createAssociations(0, 4);
        Animal[] animals = this.createAnimals(0, 4);
        
        for (User association : associations) {
            map.put(association.getUsername(), association);
        }
               
        for (Token token : this.createTokens(associations)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
        
        for (Animal animal : animals) {
            map.put(animal.getName(), animal);
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
    
    public Animal[] createAnimals(int initial,int size){
    	User association = new User("a4","s4", "a4@gmail.com", "000", "as4", "address4", "state4", "town4", "CP4", "p4");
    	map.put(association.getUsername(), association);
    	userDao.save(association);
    	Animal[] animals = new Animal[size];
    	for (int i = 0; i < size; i++) {
    		animals[i] = new Animal("name" + (i + initial), Type.CAT, "breed" + (i + initial), association, new Date(), "description" + (i + initial));
            animalDao.save(animals[i]);
        }
    	return animals;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void deleteAll() {
        genericService.deleteAllExceptAdmin();
    }
}
