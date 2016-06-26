package data.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import data.entities.Animal;
import data.entities.Type;
import data.entities.User;

public interface AnimalDao extends JpaRepository<Animal, Integer> {

	public Animal findById(int id);
	
	public List<Animal> findByBreed(String breed);
	
    public List<Animal> findByType(Type type);
	
	public List<Animal> findByAssociation(User association);

	@Modifying
	@Query("update Animal a set a.name=?2, a.type=?3, a.breed=?4, a.description=?5 where a.id=?1")
    public void modifyAnimal(int id, String name, Type type, String breed, String description);

	@Query(value = "select * from Animal where publishDate >= date_sub(NOW(), INTERVAL 31 DAY) LIMIT 8", nativeQuery=true)
	public List<Animal> findNewAnimals();
}
