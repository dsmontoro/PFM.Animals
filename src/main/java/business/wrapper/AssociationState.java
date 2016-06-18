package business.wrapper;

import data.entities.User;

public class AssociationState {

    private String username;

    private String name;

    private String address;

    private String imgName;

    public AssociationState() {
    }

    public AssociationState(String username, String name, String address, String imgName) {
        super();
        this.username = username;
        this.name = name;
        this.address = address;
        this.imgName = imgName;
    }

    public AssociationState(User association) {
        this(association.getUsername(), association.getName(), association.getAddress(), association.getImgName());
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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "AssociationState [username=" + username + ", name=" + name + ", address=" + address + ", imgName=" + imgName + "]";
    }

}
