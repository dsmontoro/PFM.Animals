package data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Photo {
	
	@Id
    @GeneratedValue
    private int id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private Animal animal;
	
	public Photo(){	
	}
	
	public Photo(String name, Animal animal) {
		this.name = name;
		this.animal = animal;
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

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
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
        return id == ((Photo) obj).id;
    }

    @Override
    public String toString() {
        return "Photo [id=" + id + ", name=" + name + ", animal=" + animal + "]";
    }
	

}
