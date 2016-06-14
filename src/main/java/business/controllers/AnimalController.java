package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.AnimalWrapper;
import data.daos.AnimalDao;
import data.entities.Animal;

@Controller

public class AnimalController {

	private AnimalDao animalDao;
	
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
        
            Animal animal = new Animal(animalWrapper.getName(), animalWrapper.getTipo() , animalWrapper.getRaza() , animalWrapper.getIdUser());
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
}
