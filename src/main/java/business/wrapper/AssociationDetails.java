package business.wrapper;

import data.entities.User;

public class AssociationDetails {

    private int id;
    
    private String username;

    private String name;
    
    private String email;
    
    private String address;

    public AssociationDetails() {
    }

    public AssociationDetails(int id, String username, String name, String email, String address) { 
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public AssociationDetails(User association) {
        this(association.getId(), association.getUsername(), association.getName(), association.getEmail(), association.getAddress());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
