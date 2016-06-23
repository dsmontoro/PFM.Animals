package business.wrapper;

import java.util.Calendar;
import java.util.List;

import data.entities.Photo;
import data.entities.User;

public class AnimalWrapper {

	private String name;
	
	private String type;
	
	private String breed;
	
	private User association;
	
	private Calendar birthdate;
	
    private Calendar publishDate;
	
    private String description;
    
    private List<Photo> images;
    
    public AnimalWrapper(){
    	
    }
    
    public AnimalWrapper(String name, String type, String breed, User association, Calendar birthdate, Calendar publishDate, String description, List<Photo> images){
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.association = association;
		this.birthdate = birthdate;
		this.publishDate = publishDate;
		this.description = description;
		this.images = images;
	}
	    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public User getAssociation() {
		return association;
	}

	public void setAssociation(User association) {
		this.association = association;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public Calendar getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Calendar publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Photo> getImages() {
		return images;
	}

	public void setImages(List<Photo> images) {
		this.images = images;
	}

	@Override
	public String toString() {
	        return "AnimalWrapper [name=" + name + ", type=" + type + ", breed=" + breed + ", association=" + association + ", publishDate=" + publishDate + ", birthdate=" + birthdate +  ", description=" + description +  "]";
	}
}