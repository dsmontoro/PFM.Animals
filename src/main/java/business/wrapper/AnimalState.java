package business.wrapper;

import java.util.Date;
import java.util.List;

import data.entities.Type;

public class AnimalState {

    private int id;
	
	private String name;
	
	private Type type;
	
	private String breed;
	
	private AssociationDetails association;
	
	private Date birthdate;	
	
    private String description;
    
    private List<PhotoState> images;
        
    public AnimalState(){
    	
    }
    
    public AnimalState(int id, String name, Type type, String breed, AssociationDetails association, Date birthdate, String description, List<PhotoState> images){
		this.id = id;
    	this.name = name;
		this.type = type;
		this.breed = breed;
		this.association = association;
		this.birthdate = birthdate;
		this.description = description;
		this.images = images;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AssociationDetails getAssociation() {
		return association;
	}

	public void setAssociation(AssociationDetails association) {
		this.association = association;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PhotoState> getImages() {
		return images;
	}

	public void setImages(List<PhotoState> images) {
		this.images = images;
	}
	    
	
}
