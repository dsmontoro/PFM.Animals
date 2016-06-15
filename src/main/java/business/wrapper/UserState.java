package business.wrapper;

import data.entities.User;

public class UserState {

    private String username;

    private String name;

    private String imgName;

    public UserState() {
    }

    public UserState(String username, String name, String imgName) {
        this.username = username;
        this.name = name;
        this.imgName = imgName;
    }

    public UserState(User user) {
        this(user.getUsername(), user.getName(), user.getImgName());
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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "UserState [username=" + username + ", name=" + name + ", imgName=" + imgName + "]";
    }

}
