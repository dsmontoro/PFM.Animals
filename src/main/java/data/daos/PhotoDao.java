package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer>{

}
