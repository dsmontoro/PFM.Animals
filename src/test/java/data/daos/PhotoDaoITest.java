package data.daos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Animal;
import data.entities.Photo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class PhotoDaoITest {
    
    @Autowired
    private PhotoDao photoDao;
    
    @Autowired
    private DaosService daosService;
    
    
    @Test 
    public void testCreate() {
        Animal animal = (Animal) daosService.getMap().get("name1");
        Photo photo = new Photo("imageName",animal);
        photoDao.save(photo);
        
        assertTrue(photoDao.count() >= 1);
    }
    
    @Test
    public void testFindByAnimal() {
        Animal animal = (Animal) daosService.getMap().get("name1");
        Photo photo = new Photo("imageName1",animal);
        photoDao.save(photo);
        photo = new Photo("imageName2",animal);
        photoDao.save(photo);
        
        assertTrue(photoDao.findByAnimal(animal).size() == 2);
    }

}
