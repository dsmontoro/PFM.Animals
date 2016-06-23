package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Animal;
import data.entities.Type;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AnimalDaoITest {

	@Autowired
	private AnimalDao animalDao;
	
	@Autowired
	private DaosService daosService;
	
	@Test 
    public void testCreate() {
        assertTrue(animalDao.count() >= 4);
    }
	
	@Test
    public void testFindByAnimalId() {
        Animal a1 = (Animal) daosService.getMap().get("name1");
        assertEquals(a1.getId(), animalDao.findByAnimalId(a1.getId()).getId());
        assertNull(animalDao.findByAnimalId(0));
    }
	
	@Test
	public void testFindByRaza(){
		assertEquals(1, animalDao.findByRaza("breed1").size());
	}
	
	@Test
	public void testFindByTipo(){
		assertEquals(4, animalDao.findByTipo(Type.CAT).size());
	}
	
	@Test
	public void testFindByTipoAndRaza(){
		assertEquals(1, animalDao.findByTipoAndRaza(Type.CAT,"breed2").size());
		assertEquals(0, animalDao.findByTipoAndRaza(Type.DOG,"breed2").size());
	}
	
	@Test
	public void testFindByUser(){
		User association = (User) daosService.getMap().get("a4");
		assertEquals(4, animalDao.findByAssociation(association).size());
	}
}
