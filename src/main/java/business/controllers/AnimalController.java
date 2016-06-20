package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.AnimalWrapper;
import data.daos.AnimalDao;
import data.daos.AuthorizationDao;
import data.daos.UserDao;
import data.entities.Animal;
import data.entities.Role;

@Controller

public class AnimalController {

	private AnimalDao animalDao;
	
	private UserDao userDao;
	
	private AuthorizationDao authorizationDao;
	
	@Autowired
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }
	
	public List<Animal> showAnimals() {
        List<Animal> animalList = new ArrayList<>();
        for (Animal animal : animalDao.findAll()) {
            animalList.add(animal);
        }
        return animalList;
    }
	
	public boolean registration(AnimalWrapper animalWrapper) {
        
            Animal animal = new Animal(animalWrapper.getName(), animalWrapper.getTipo() , animalWrapper.getRaza() , animalWrapper.getIdUser() , animalWrapper.getAge() , animalWrapper.getDescription());
            animalDao.save(animal);
            return true;        
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
	
	public boolean existUser(String email){
		return (userDao.findByUsernameOrEmail(email) != null);
	}
	
	public boolean userHasRole(String email, Role role){
		return (authorizationDao.findRoleByUser(userDao.findByUsernameOrEmail(email)).get(0) == role);
	}
}
