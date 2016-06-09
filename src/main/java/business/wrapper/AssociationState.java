package business.wrapper;

import data.entities.User;

public class AssociationState {

    private String username;

    private String name;
    
    private String address;

    public AssociationState() {
    }

    public AssociationState(String username, String name, String address) { 
        super();
        this.username = username;
        this.name = name;
        this.address = address;
    }

    public AssociationState(User association) {
        this(association.getUsername(), association.getName(), association.getAddress());
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
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserState [username=" + username + ", name=" + name + ", address=" + address + "]";
    }
}
