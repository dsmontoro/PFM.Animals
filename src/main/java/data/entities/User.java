package data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = true)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;   

    @Column(nullable = true)
    private String phone;
    
    @Column(nullable = true)
    private String association;
    
    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String state;
    
    @Column(nullable = true)
    private String town;
    
    @Column(nullable = true)
    private String postalCode;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = true)
    private String imgName;
    

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.surname = null;
        this.email = email;
        this.phone = null;
        this.association = null;
        this.address = null;
        this.state = null;
        this.town = null;
        this.postalCode = null;        
        this.password = new BCryptPasswordEncoder().encode(password);
        this.imgName = null;
    }

    public User(String username, String surname, String email, String phone, String association, String address, String state, String town, String postalCode, String password) {
    	this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.association = association;
        this.address = address;
        this.state = state;
        this.town = town;
        this.postalCode = postalCode;        
        this.password = new BCryptPasswordEncoder().encode(password);
        this.imgName = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
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

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

    public String getPassword() {
        return password;
    }
    
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
        return id == ((User) obj).id;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", surname=" + surname + ", email=" + email + ", phone=" + phone + 
        		", association=" + association + ", address=" + address + ", state=" + state + ", town=" + town + 
        		", postalCode=" + postalCode + ", password=" + password +", imgName=" + imgName + "]";
    }

}
