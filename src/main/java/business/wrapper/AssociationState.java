package business.wrapper;

import data.entities.User;

public class AssociationState {

    private int id;

    private String username;

    private String address;

    private String imgName;

    private String association;

    public AssociationState() {
    }

    public AssociationState(int id, String username, String address, String imgName, String association) {
        super();
        this.id = id;
        this.username = username;
        this.address = address;
        this.imgName = imgName;
        this.association = association;
    }

    public AssociationState(User association) {
        this(association.getId(), association.getUsername(), association.getAddress(), association.getImgName(),
                association.getAssociation());
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

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "AssociationState [id=" + id + ", username=" + username + ", address=" + address + ", imgName=" + imgName + ", association="
                + association + "]";
    }

}
