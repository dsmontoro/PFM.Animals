package business.wrapper;

public class UserWrapper {

    private String username;

    private String email;

    private String password;

    private String confirmedPassword;

    private String name;

    private String address;

    private String imgName;

    public UserWrapper() {
    }

    public UserWrapper(String username, String email, String password, String confirmedPassword, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.name = name;
        this.address = null;
        this.imgName = null;
    }

    public UserWrapper(String username, String email, String password, String confirmedPassword, String name, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.name = name;
        this.address = address;
        this.imgName = null;
    }
    
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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
        return "UserWrapper [username=" + username + ", email=" + email + ", password=" + password + ", name=" + name + ", address="
                + address + "]";
    }

}
