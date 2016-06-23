package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import business.api.exceptions.InvalidAnimalUserEception;
import business.api.exceptions.NotFoundAnimalException;
import business.controllers.AnimalController;
import business.wrapper.AnimalWrapper;
import data.entities.Animal;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.ANIMALS)
public class AnimalResource {

	
	private AnimalController animalController;
		
    @Autowired
    public void setAnimalController(AnimalController animalController) {
        this.animalController = animalController;
    }
       
    @RequestMapping(method = RequestMethod.GET)
    public List<Animal> showAnimals() {
        return animalController.showAnimals();
    }
    
    private void validateField(String field, String msg) throws InvalidAnimalUserEception {
        if (field == null || field.isEmpty()) {
            throw new InvalidAnimalUserEception(msg);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "content-type=multipart/*")
    public void registration(AnimalWrapper animalWrapper, @RequestParam(value = "image", required = false) MultipartFile[] images) throws InvalidAnimalUserEception{    	
            	   	
    	validateField(animalWrapper.getName(), "name");
        validateField(animalWrapper.getBreed(), "breed");
        if (!this.animalController.registration(animalWrapper, images)) {
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
