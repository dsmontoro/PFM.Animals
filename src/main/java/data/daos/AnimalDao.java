package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import data.entities.Animal;
import data.entities.Type;
import data.entities.User;

public interface AnimalDao extends JpaRepository<Animal, Integer> {

	@Query("select animal from Animal animal where animal.id = ?1")
    public Animal findByAnimalId(int id);
	
	@Query("select animal from Animal animal where animal.breed = ?1")
    public List<Animal> findByRaza(String raza);
	
	@Query("select animal from Animal animal where animal.type = ?1")
    public List<Animal> findByTipo(Type tipo);
	
	@Query("select animal from Animal animal where animal.type = ?1 and animal.breed = ?2")
    public List<Animal> findByTipoAndRaza(Type type, String breed);
	
	@Query("select animal from Animal animal where animal.association = ?1")
	public List<Animal> findByAssociation(User association);

	@Modifying
	@Query("update Animal a set a.name=?2, a.type=?3, a.breed=?4, a.description=?5 where a.id=?1")
    public void modifyAnimal(int id, String name, Type type, String breed, String description);
}
