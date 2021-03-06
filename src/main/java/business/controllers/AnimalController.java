package business.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import business.wrapper.AnimalState;
import business.wrapper.AnimalWrapper;
import business.wrapper.AssociationDetails;
import business.wrapper.PhotoState;
import data.daos.AnimalDao;
import data.daos.PhotoDao;
import data.daos.UserDao;
import data.entities.Animal;
import data.entities.Photo;
import data.entities.Type;
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
        	List<PhotoState> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findByAnimal(animal)) {
        		PhotoState photoState = new PhotoState(image);
        		imageList.add(photoState);
        	}
        	AssociationDetails association = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getId(), animal.getName(), animal.getType(), animal.getBreed(), association, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
        }
        return animalList;
    }
    
    public List<AnimalState> showNewAnimals() {
    	List<AnimalState> animalList = new ArrayList<>();
    	for (Animal animal : animalDao.findNewAnimals()) {
    		List<PhotoState> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findByAnimal(animal)) {
        		PhotoState photoState = new PhotoState(image);
        		imageList.add(photoState);
        	}
        	AssociationDetails association = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getId(), animal.getName(), animal.getType(), animal.getBreed(), association, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
    	}
    	return animalList;
    }

    public List<AnimalState> showAssociationAnimals(int id) {
    	User association = userDao.findUserById(id);
    	List<AnimalState> animalList = new ArrayList<>();
    	for (Animal animal : animalDao.findByAssociation(association)) {
    		List<PhotoState> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findByAnimal(animal)) {
        		PhotoState photoState = new PhotoState(image);
        		imageList.add(photoState);
        	}
        	AssociationDetails associationDetails = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getId(), animal.getName(), animal.getType(), animal.getBreed(), associationDetails, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
    	}
    	return animalList;
	}
    
    public List<AnimalState> showTypeAnimals(Type type){
    	List<AnimalState> animalList = new ArrayList<>();
    	
    	for(Animal animal : animalDao.findByType(type)){
    		List<PhotoState> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findByAnimal(animal)) {
        		PhotoState photoState = new PhotoState(image);
        		imageList.add(photoState);
        	}
        	AssociationDetails associationDetails = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getId(), animal.getName(), animal.getType(), animal.getBreed(), associationDetails, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
    	}
    	
    	return animalList;
    }
    
    public List<AnimalState> showBreedAnimals(String breed){
    	List<AnimalState> animalList = new ArrayList<>();
    	
    	for(Animal animal : animalDao.findByBreed(breed)){
    		List<PhotoState> imageList = new ArrayList<>();
        	for (Photo image : photoDao.findByAnimal(animal)) {
        		PhotoState photoState = new PhotoState(image);
        		imageList.add(photoState);
        	}
        	AssociationDetails associationDetails = new AssociationDetails(animal.getAssociation());
        	AnimalState animalState= new AnimalState(animal.getId(), animal.getName(), animal.getType(), animal.getBreed(), associationDetails, animal.getBirthdate(), animal.getDescription(), imageList);
            animalList.add(animalState);
    	}
    	
    	return animalList;
    }
    
    public boolean registration(int id, AnimalWrapper animalWrapper, MultipartFile[] images) {
        User association = userDao.findUserById(id);
        if (association != null) {
            Animal animal = new Animal(animalWrapper.getName(), animalWrapper.getType(), animalWrapper.getBreed(), association,
                    animalWrapper.getBirthdate(), animalWrapper.getDescription());
            animalDao.save(animal);
            uploadImagesAnimal(animal, images);
            return true;
        } else {
            return false;
        }

    }

    public void uploadImagesAnimal(Animal animal, MultipartFile[] images) {
        String randomName = "";
        if (images != null && images.length > 0) {
            for (int i = 0; i < images.length; i++) {
                MultipartFile image = images[i];
                if (image != null && !image.isEmpty()) {
                    try {
                        validateImage(image);
                    } catch (RuntimeException re) {
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

    public boolean existsAnimal(int id) {
        if (animalDao.exists(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAnimal(int id) {
        if (!existsAnimal(id)) {
            return false;
        } else {
            for (Photo photo : photoDao.findByAnimal(animalDao.findById(id))) {
                photoDao.delete(photo);
            }
            animalDao.delete(id);
            return true;
        }
    }

    public boolean modifyAnimal(AnimalWrapper animal, int id) {
        if (!existsAnimal(id)) {
            return false;
        } else {
            animalDao.modifyAnimal(id, animal.getName(), animal.getType(), animal.getBreed(), animal.getDescription());
            return true;
        }
    }

	public AnimalState showAnimal(int id) {
		Animal a = animalDao.findById(id);
		List<PhotoState> photos = new ArrayList<>();
		for (Photo photo: photoDao.findByAnimal(a)) {
			PhotoState photoState = new PhotoState(photo);			
			photos.add(photoState);
		}		
		return new AnimalState(a.getId(), a.getName(), a.getType(), a.getBreed(), new AssociationDetails(a.getAssociation()), a.getBirthdate(), a.getDescription(), photos);
	}
	
}
