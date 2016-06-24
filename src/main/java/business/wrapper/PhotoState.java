package business.wrapper;

import data.entities.Photo;

public class PhotoState {
	
	private int id;
	
	private String name;
	
	public PhotoState() {
		
	}
	
	public PhotoState(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public PhotoState(Photo photo) {
		this(photo.getId(),photo.getName());
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
}
