package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.User;

public interface AnimalDao extends JpaRepository<User, Integer> {

	
}
