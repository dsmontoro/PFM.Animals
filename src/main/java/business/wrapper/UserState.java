package business.wrapper;

import data.entities.User;

public class UserState {

    private String username;

    private String imgName;

    public UserState() {
    }

    public UserState(String username, String imgName) {
        this.username = username;
        this.imgName = imgName;
    }

    public UserState(User user) {
        this(user.getUsername(), user.getImgName());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "UserState [username=" + username + ", imgName=" + imgName + "]";
    }

}
