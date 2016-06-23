package business.wrapper;

import java.util.Calendar;

import data.entities.Type;
import data.entities.User;

public class AnimalWrapper {

	private String name;
	
	private Type type;
	
	private String breed;
	
	private User association;
	
	private Calendar birthdate;
	
    private Calendar publishDate;
	
    private String description;
        
    public AnimalWrapper(){
    	
    }
    
    public AnimalWrapper(String name, Type type, String breed, User association, Calendar birthdate, Calendar publishDate, String description){
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.association = association;
		this.birthdate = birthdate;
		this.publishDate = publishDate;
		this.description = description;
	}
	    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
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

	@Override
	public String toString() {
	        return "AnimalWrapper [name=" + name + ", type=" + type + ", breed=" + breed + ", association=" + association + ", publishDate=" + publishDate + ", birthdate=" + birthdate +  ", description=" + description +  "]";
	}
}
