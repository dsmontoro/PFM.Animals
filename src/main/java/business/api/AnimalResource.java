package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.InvalidAnimalUserEception;
import business.api.exceptions.NotFoundAnimalException;
import business.controllers.AnimalController;
import business.wrapper.AnimalState;
import business.wrapper.AnimalWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.ANIMALS)
public class AnimalResource {

	
	private AnimalController animalController;
		
    @Autowired
    public void setAnimalController(AnimalController animalController) {
        this.animalController = animalController;
    }
       
    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalState> showAnimals() {
        return animalController.showAnimals();
    }
    
    @RequestMapping(value = Uris.ASSOCIATIONS + Uris.ID, method = RequestMethod.POST)
    public void registration(@PathVariable int id, @RequestBody AnimalWrapper animalWrapper) throws InvalidAnimalUserEception{    	      	   	
    	validateField(animalWrapper.getName(), "name");
        validateField(animalWrapper.getBreed(), "breed");                       
        if (!this.animalController.registration(id, animalWrapper)) {
            throw new InvalidAnimalUserEception();
        }
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws NotFoundAnimalException
    {
    	if(!animalController.deleteAnimal(id)){
    		throw new NotFoundAnimalException();
    	}
    }
   
    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public void modify(@RequestBody AnimalWrapper animal, @PathVariable int id) throws NotFoundAnimalException {
        if(!animalController.modifyAnimal(animal,id)) {
            throw new NotFoundAnimalException();
        }
    }
    
    private void validateField(String field, String msg) throws InvalidAnimalUserEception {
        if (field == null || field.isEmpty()) {
            throw new InvalidAnimalUserEception(msg);
        }
    }
}
