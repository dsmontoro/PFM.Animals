package business.wrapper;

import java.util.Date;

public class AnimalWrapper {

	private String name;
	
	private String tipo;
	
	private String raza;
	
	private int idUser;
	
    private Date publishDate;
	
    private int age;
	
    private String description;
    
    public AnimalWrapper(){
    	
    }
    
    public AnimalWrapper(String name,String tipo,String raza,int idUser){
		this.name = name;
		this.tipo = tipo;
		this.raza = raza;
		this.idUser = idUser;
		this.publishDate = new java.util.Date();
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
	        return "AnimalWrapper [name=" + name + ", tipo=" + tipo + ", raza=" + raza + ", idUser=" + idUser + ", publishDate=" + publishDate + ", age=" + age +  ", description=" + description +  "]";
	}
}
