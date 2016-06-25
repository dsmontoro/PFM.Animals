package data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.daos.AnimalDao;
import data.daos.AuthorizationDao;
import data.daos.TokenDao;
import data.daos.UserDao;

@Service
public class DataService {
    
    @Autowired
    private Populate populate;

    @Autowired
    private AuthorizationDao authorizationDao;
    
    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private AnimalDao animalDao;
    

    public void deleteAllExceptAdmin(){
        authorizationDao.deleteAll();
        tokenDao.deleteAll();
        userDao.deleteAll();
        animalDao.deleteAll();
        populate.createDefaultAdmin();
    }

}
