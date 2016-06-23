package business.wrapper;

import java.util.Calendar;
import java.util.Date;

import data.entities.Type;

public class AnimalWrapper {

	private String name;
	
	private Type type;
	
	private String breed;
	
	private int idAssociation;
	
	private Date birthdate;
	
	
    private String description;
        
    public AnimalWrapper(){
    	
    }
    
    public AnimalWrapper(String name, Type type, String breed, int idAssociation, Date birthdate,String description){
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.idAssociation = idAssociation;
		this.birthdate = birthdate;
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

	public int getIdAssociation() {
		return idAssociation;
	}

	public void setAssociation(int idAssociation) {
		this.idAssociation = idAssociation;
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

	
}
