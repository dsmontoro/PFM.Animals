package business.wrapper;

import data.entities.User;

public class AssociationDetails {

    private int id;

    private String username;
    
    private String surname;

    private String email;
    
    private String phone;
    
    private String association;

    private String address;
    
    private String state;
    
    private String town;    
    
    private String postalCode;
    
    private String imgName;

    public AssociationDetails() {
    }

    public AssociationDetails(int id, String username, String surname, String email, String phone, String association, String address, String state, String town, String postalCode, String imgName) {
        super();
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.association = association;
        this.address = address;
        this.state = state;
        this.town = town;
        this.postalCode = postalCode;
        this.imgName = imgName;
    }

    public AssociationDetails(User association) {
        this(association.getId(), association.getUsername(), association.getSurname(), 
        		association.getEmail(), association.getPhone(), association.getAssociation(),
        		association.getAddress(), association.getState(), association.getTown(),
                association.getPostalCode(), association.getImgName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    
    
    public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "AssociationDetails [id=" + id + ", username=" + username + ", surname=" + surname 
        		+ ", email=" + email + ", phone=" + phone + ", address=" + address
                + ", state=" + state + ", town=" + town + ", postalCode=" + postalCode
        		+ ", imgName=" + imgName + "]";
    }

}
