package business.wrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.entities.Photo;
import data.entities.User;

public class AnimalWrapperBuilder {

	private AnimalWrapper animalWrapper;
	
	public AnimalWrapperBuilder(int suffix){
		User association = new User();
		List<Photo> images = new ArrayList<>();
		animalWrapper = new AnimalWrapper("name" + suffix, "type" + suffix, "breed" + suffix, association, Calendar.getInstance(), Calendar.getInstance(), "description" + suffix, images);
	}
	
	public AnimalWrapperBuilder(){
		this(0);
	}
	
	public AnimalWrapperBuilder name(String name){
		animalWrapper.setName(name);
		return this;
	}
	
	public AnimalWrapperBuilder type(String type){
		animalWrapper.setType(type);
		return this;
	}
	
	public AnimalWrapperBuilder breed(String breed){
		animalWrapper.setBreed(breed);
		return this;
	}
	
	public AnimalWrapperBuilder association(User association) {
		animalWrapper.setAssociation(association);
		return this;
	}
	
	public AnimalWrapperBuilder publishDate(Calendar publishDate) {
		animalWrapper.setPublishDate(publishDate);
		return this;
	}
	
	public AnimalWrapperBuilder birthdate(Calendar birthdate) {
		animalWrapper.setBirthdate(birthdate);
		return this;
	}
	
	public AnimalWrapperBuilder description(String description) {
		animalWrapper.setDescription(description);
		return this;
	}
	
	public AnimalWrapperBuilder images(List<Photo> images) {
		animalWrapper.setImages(images);
		return this;
	}
	
	public AnimalWrapper build(){
		return animalWrapper;
	}
}
