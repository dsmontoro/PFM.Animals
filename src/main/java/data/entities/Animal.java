package data.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Animal {
	@Id
    @GeneratedValue
    private int id;
	
	@Column(nullable = false)
    private String name;
	
	@Enumerated(EnumType.STRING)
    private Type type;
	
	@Column(nullable = false)
    private String breed;
	
	@ManyToOne
    @JoinColumn(nullable = false)
    private User association;
	
	@Column(nullable = false)
    private Calendar publishDate;
	
	@Column(nullable = true)
    private Date birthdate;
	
	@Column(nullable = true)
    private String description;
	
	public Animal() {
    }
	
	public Animal(String name, Type type, String breed, User association, Date birthdate, String description){
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.association = association;
		this.publishDate = Calendar.getInstance();
		this.birthdate = birthdate;
		this.description = description;
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

	public User getAssociation() {
		return association;
	}

	public void setAssociation(User association) {
		this.association = association;
	}

	public Calendar getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Calendar publishDate) {
		this.publishDate = publishDate;
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
	
	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Animal) obj).id;
    }

	@Override
	public String toString() {
	        return "Animal [id=" + id + ", name=" + name + ", type=" + type + ", breed=" + breed + ", association=" + association + ", publishDate=" + publishDate + ", birthdate=" + birthdate +  ", description=" + description +  "]";
	}
}
