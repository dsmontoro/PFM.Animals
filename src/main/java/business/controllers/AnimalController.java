package business.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.AnimalState;
import business.wrapper.AnimalWrapper;
import business.wrapper.AssociationDetails;
import data.daos.AnimalDao;
import data.daos.PhotoDao;
import data.daos.UserDao;
import data.entities.Animal;
import data.entities.Photo;
import data.entities.User;

@Controller

public class AnimalController {

	@Autowired
    ServletContext servletContext;
	
	private UserDao userDao;
	
	private AnimalDao animalDao;

	private PhotoDao photoDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
	
	@Autowired
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }
	
	@Autowired
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	// class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();
	
	public List<AnimalState> showAnimals() {
        List<AnimalState> animalList = new ArrayList<>();
        for (Animal animal : animalDao.findAll()) {
        	List<Photo> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findPhotosByAnimal(animal)) {
        		imageList.add(image);
        	}
        	AssociationDetails association = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getName(), animal.getType(), animal.getBreed(), association, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
        }
        return animalList;
    }
	
	public boolean registration(int id, AnimalWrapper animalWrapper) {
	    User association = userDao.findUserById(id);
        if (association != null) {
            Animal animal = new Animal(animalWrapper.getName(),animalWrapper.getType(), animalWrapper.getBreed(), association, animalWrapper.getBirthdate(), animalWrapper.getDescription());
            animalDao.save(animal);
            return true;
        }
        else {
            return false;
        }
         
    }
			
	public boolean existsAnimal(int id){
		if(animalDao.exists(id)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean deleteAnimal(int id){
		if(!existsAnimal(id)){
			return false;
		}
		else{
			animalDao.delete(id);
			return true;
		}
	}
	
	public boolean modifyAnimal(AnimalWrapper animal, int id) {
	    if(!existsAnimal(id)) {
	        return false;
	    }
	    else {
	        animalDao.modifyAnimal(id, animal.getName(), animal.getType(), animal.getBreed(), animal.getDescription());
	        return true;
	    }
	}
}
