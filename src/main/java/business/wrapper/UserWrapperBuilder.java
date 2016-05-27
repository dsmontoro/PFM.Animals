package business.wrapper;

public class UserWrapperBuilder {

    private UserWrapper userWrapper;

    public UserWrapperBuilder(int suffix) {
        userWrapper = new UserWrapper("u" + suffix, "u" + suffix + "@gmail.com", "u" + suffix, "u" + suffix, "name" + suffix);
    }

    public UserWrapperBuilder() {
        this(0);
    }

    public UserWrapperBuilder username(String username) {
        userWrapper.setUsername(username);
        return this;
    }

    public UserWrapperBuilder email(String email) {
        userWrapper.setEmail(email);
        return this;
    }

    public UserWrapperBuilder password(String password) {
        userWrapper.setPassword(password);
        return this;
    }

    public UserWrapperBuilder name(String name) {
        userWrapper.setName(name);
        return this;
    }
    
    public UserWrapper build() {
        return userWrapper;
    }

}
