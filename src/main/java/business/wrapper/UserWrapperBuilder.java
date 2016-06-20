package business.wrapper;

public class UserWrapperBuilder {

    private UserWrapper userWrapper;

    public UserWrapperBuilder(int suffix) {
        userWrapper = new UserWrapper("u" + suffix, "s" + suffix, "u" + suffix + "@gmail.com", "00000000" + suffix, "a" + suffix, "ad" + suffix, "s" + suffix, "t" + suffix, "2802" + suffix, "u" + suffix);
    }

    public UserWrapperBuilder() {
        this(0);
    }

    public UserWrapperBuilder username(String username) {
        userWrapper.setUsername(username);
        return this;
    }
    
    public UserWrapperBuilder surname(String surname) {
        userWrapper.setSurname(surname);
        return this;
    }

    public UserWrapperBuilder email(String email) {
        userWrapper.setEmail(email);
        return this;
    }
    
    public UserWrapperBuilder phone(String phone) {
    	userWrapper.setPhone(phone);
    	return this;
    }
    
    public UserWrapperBuilder address(String address) {
        userWrapper.setAddress(address);
        return this;
    }

    public UserWrapperBuilder state(String state) {
        userWrapper.setState(state);
        return this;
    }
    
    public UserWrapperBuilder town(String town) {
    	userWrapper.setTown(town);
    	return this;
    }
    
    public UserWrapperBuilder postalCode(String postalCode) {
    	userWrapper.setPostalCode(postalCode);
    	return this;
    }
    
    public UserWrapperBuilder password(String password) {
        userWrapper.setPassword(password);
        return this;
    }
    
    public UserWrapper build() {
        return userWrapper;
    }

}
