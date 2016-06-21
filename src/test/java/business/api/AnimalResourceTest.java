package business.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.event.PublicInvocationEvent;

import business.controllers.AnimalController;

public class AnimalResourceTest {

	private AnimalResource animalResource;
	
	private AnimalController animalController;
	
	@Before
    public void before() {
		animalController = new AnimalControllerMock();
		animalResource = new AnimalResource();
		animalResource.setAnimalController(animalController);
    }
	
	
	@Test
	public void testShowAnimals(){
		
	}
}
