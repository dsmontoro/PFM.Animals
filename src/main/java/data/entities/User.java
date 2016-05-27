package data.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String confirmedPassword;

    @Column(nullable = false)
    private String name;
    
    public User() {
    }

    public User(String username, String email, String password, String confirmedPassword, String name) {
        this.username = username;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.confirmedPassword = new BCryptPasswordEncoder().encode(confirmedPassword);
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((User) obj).id;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password=" + password + "]";
    }

}
