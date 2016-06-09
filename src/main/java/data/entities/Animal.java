package data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Animal {
	@Id
    @GeneratedValue
    private int id;
	
	@Column(unique = false, nullable = false)
    private String name;
	
	@Column(unique = false, nullable = false)
    private String tipo;
	
	@Column(unique = false, nullable = false)
    private String raza;
	
	@Column(unique = false, nullable = false)
    private int idUser;
	
	@Column(unique = false, nullable = false)
    private Date publishDate;
	
	@Column(unique = false, nullable = true)
    private String city;
	
	@Column(unique = false, nullable = true)
    private Date district;
	
	@Column(unique = false, nullable = true)
    private int age;
	
	@Column(unique = false, nullable = true)
    private String description;
	
	public Animal() {
    }
	
	public Animal(String name,String tipo,String raza,int idUser){
		this.name = name;
		this.tipo = tipo;
		this.raza = raza;
		this.idUser = idUser;
		this.publishDate = new java.util.Date();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDistrict() {
		return district;
	}

	public void setDistrict(Date district) {
		this.district = district;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
	        return "Animal [id=" + id + ", name=" + name + ", tipo=" + tipo + ", raza=" + raza + ", idUser=" + idUser + ", publishDate=" + publishDate +  ", city=" + city +  ", district=" + district +  ", age=" + age +  ", description=" + description +  "]";
	}
}
