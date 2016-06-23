package business.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import business.wrapper.AnimalWrapper;
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
	
	public List<Animal> showAnimals() {
        List<Animal> animalList = new ArrayList<>();
        for (Animal animal : animalDao.findAll()) {
            animalList.add(animal);
        }
        return animalList;
    }
	
	public boolean registration(AnimalWrapper animalWrapper, MultipartFile[] images) {
        
	    User association = userDao.findUserById(animalWrapper.getIdAssociation());
		Animal animal = new Animal(animalWrapper.getName(), animalWrapper.getType() , animalWrapper.getBreed() , association , animalWrapper.getBirthdate() , animalWrapper.getDescription());
        animalDao.save(animal);
				 
        String randomName = "";
        if (images != null && images.length > 0) { 
        	for (int i = 0; i < images.length; i++) { 
        		MultipartFile image = images[i]; 
        		if (image != null && !image.isEmpty()) {
        			try { 
        				validateImage(image); 
        			}
        			catch (RuntimeException re) {
        				throw new RuntimeException(re.toString());
        			}
        			try { 
        				randomName = randomIdentifier(); 
        				saveImage(randomName, image);
        				Photo photo = new Photo(randomName, animal);
        				photoDao.save(photo);
        			} catch (IOException e) { 
        				throw new RuntimeException("Only JPG images are accepted");
        			}
        		}
        	}
        }        
        return true;        
    }
	
	private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }
    
    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++)
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            if (identifiers.contains(builder.toString()))
                builder = new StringBuilder();
        }
        return builder.toString();
    }

    private void saveImage(String filename, MultipartFile image) throws RuntimeException, IOException {
        try {
            File file = new File(servletContext.getRealPath("/") + "/images/" + filename + ".jpg");
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
        } catch (IOException e) {
            throw e;
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
