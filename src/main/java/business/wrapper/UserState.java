package business.wrapper;

import data.entities.User;

public class UserState {
    
    private String username;

    private String name;

    public UserState() {
    }

    public UserState(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public UserState(User user) {
        this(user.getUsername(), user.getName());
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

    @Override
    public String toString() {
        return "UserState [username=" + username + ", name=" + name + "]";
    }
    
}
