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
import business.api.validation.AnimalValidator;
import business.controllers.AnimalController;
import business.wrapper.AnimalWrapper;
import data.entities.Animal;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.ANIMALS)
public class AnimalResource {

	
	private AnimalController animalController;
	
	//private AnimalValidator animalValidator;

    @Autowired
    public void setAnimalController(AnimalController animalController) {
        this.animalController = animalController;
    }
    
    /*@Autowired
    public void setAnimalValidator(AnimalValidator animalValidator) {
		this.animalValidator = animalValidator;
	}*/
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Animal> showAnimals() {
        return animalController.showAnimals();
    }
    
    private void validateField(String field, String msg) throws InvalidAnimalUserEception {
        if (field == null || field.isEmpty()) {
            throw new InvalidAnimalUserEception(msg);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void registration(@RequestBody AnimalWrapper animalWrapper) throws InvalidAnimalUserEception{    	
        validateField(animalWrapper.getName(), "name");
        validateField(animalWrapper.getTipo(), "tipo");
        validateField(animalWrapper.getRaza(), "raza");
        if (!this.animalController.registration(animalWrapper)) {
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
}
