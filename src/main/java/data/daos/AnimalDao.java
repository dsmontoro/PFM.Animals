package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Animal;

public interface AnimalDao extends JpaRepository<Animal, Integer> {

	@Query("select animal from Animal where animal.id = ?1")
    public Animal findByAnimalId(String id);
	
	@Query("select animal from Animal where animal.raza = ?1")
    public List<Animal> findByRaza(String raza);
	
	@Query("select animal from Animal where animal.tipo = ?1")
    public List<Animal> findByTipo(String tipo);
	
	@Query("select animal from Animal where animal.tipo = ?1 and animal.raza = ?2")
    public List<Animal> findByTipoAndRaza(String tipo,String raza);
	
	@Query("select animal from Animal where animal.idUser = ?1")
    public List<Animal> findByUser(String idUser);
}
