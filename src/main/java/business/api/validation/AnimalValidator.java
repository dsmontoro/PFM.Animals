package business.api.validation;

import org.springframework.beans.factory.annotation.Autowired;

import business.api.exceptions.InvalidUserGrantException;
import business.api.exceptions.NotFoundUserIdException;
import business.controllers.AnimalController;
import data.entities.Role;

public class AnimalValidator {

	private AnimalController animalController;
	
	@Autowired
	public void setAnimalController(AnimalController animalController){
		this.animalController = animalController;
	}
	
	public AnimalValidator(){
		
	}
	
	public void validateUser(String userEmail,Role grant) throws NotFoundUserIdException,InvalidUserGrantException{
		if(!animalController.existUser(userEmail)){
			throw new NotFoundUserIdException();
		}
		if(!animalController.userHasRole(userEmail, grant)){
			throw new InvalidUserGrantException();
		}
	}
}
