package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
    
    @Query("select token.user from Token token where token.value = ?1 and token.expiredDate >= NOW()")
    public User findByTokenValue(String tokenValue);

    @Query("select user from User user where user.username = ?1 or user.email = ?1")
    public User findByUsernameOrEmail(String id);
    
    @Query("select user from User user where user.address = null and user.username != 'admin' ")
    public List<User> findAllUsers();
    
    @Query("select user from User user where user.address != null")
    public List<User> findAllAssociations();

    User findUserById(int id);

}
