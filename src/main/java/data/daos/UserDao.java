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
    
    @Query("select user from User user where user.address != null")
    public List<User> findAllAssociations();

    User findUserById(int id);

    @Query("select user from User user where user.address != null and user.association like CONCAT('%',?1,'%')")
    public List<User> searchAssociationsByName(String name);
    
    @Query("update User u set u.username=?2, u.surname=?3, u.email=?4, u.phone=?5, u.association=?6, u.address=?7, u.state=?8, u.town=?9, u.postalCode=?10, u.password=?11 where u=?1")
	public void modifyAssociation(User user, String username, String surname, String email, String phone,
			String association, String address, String state, String town, String postalCode, String password);

}
