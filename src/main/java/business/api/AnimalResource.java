package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.AnimalController;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.ANIMALS)
public class AnimalResource {

	
	private AnimalController animalControllerController;

    @Autowired
    public void setAnimalController(AnimalController animalController) {
        this.animalControllerController = animalController;
    }
    
}
