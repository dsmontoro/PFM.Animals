package business.wrapper;

public class UserWrapper {

    private String username;
    
    private String surname;

    private String email;
    
    private String phone;
    
    private String association;
    
    private String address;

    private String state;
    
    private String town;
    
    private String postalCode;
    
    private String password;
    
    private String imgName;

    public UserWrapper() {
    }

    public UserWrapper(String username, String email, String password) {
    	this.username = username;
        this.surname = null;
        this.email = email;
        this.phone = null;
        this.association = null;
        this.address = null;
        this.state = null;
        this.town = null;
        this.postalCode = null;
        this.password = password;
        this.imgName = null;
    }

    public UserWrapper(String username, String surname, String email, String phone, String association, String address, String state, String town, String postalCode, String password) {
    	this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.association = association;
        this.address = address;
        this.state = state;
        this.town = town;
        this.postalCode = postalCode;
        this.password = password;
        this.imgName = null;
    }
    
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "UserWrapper [username=" + username + ", surname=" + surname 
        		+ ", email=" + email + ", phone=" + phone + ", address=" + address 
        		+ ", state=" + state + ", town=" + town + ", postalCode=" + postalCode
        		+ ", password=" + password + ",imgName=" + imgName +"]";
    }

}
