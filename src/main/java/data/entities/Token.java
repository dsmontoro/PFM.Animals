package data.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
            
    @Column(nullable = false)
    private Calendar expiredDate;

    public Token() {
    }

    public Token(User user) {
        assert user != null;
        this.user = user;
        this.value = new Encrypt().encryptInBase64UrlSafe("" + user.getId() + user.getUsername() + Long.toString(new Date().getTime()) + user.getPassword());
        this.expiredDate = Calendar.getInstance();
        this.expiredDate.add(Calendar.HOUR, 1);
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
    
    public Calendar getExpiredDate() {
        return expiredDate;
    }
    
    public void setExpiredDate(Calendar expiredDate) {
        this.expiredDate = expiredDate;
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
        return id == ((Token) obj).id;
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("dd-MMM-yyyy ").format(expiredDate.getTime());
        return "Token [id=" + id + ", value=" + value + ", expiredDate="+ date + "]";
    }
}
