package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Animal;
import data.entities.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer>{

	List<Photo> findByAnimal(Animal animal);

}
