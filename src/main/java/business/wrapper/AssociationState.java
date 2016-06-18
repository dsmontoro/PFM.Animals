package business.wrapper;

import data.entities.User;

public class AssociationState {

    private int id;

    private String username;

    private String name;

    private String address;

    private String imgName;

    public AssociationState() {
    }

    public AssociationState(int id, String username, String name, String address, String imgName) {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.imgName = imgName;
    }

    public AssociationState(User association) {
        this(association.getId(), association.getUsername(), association.getName(), association.getAddress(), association.getImgName());
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
        return "AssociationState [id=" + id + ", username=" + username + ", name=" + name + ", address=" + address + ", imgName=" + imgName
                + "]";
    }

}
