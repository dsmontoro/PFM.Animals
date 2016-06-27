package business.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import business.api.exceptions.InvalidAnimalUserEception;
import business.api.exceptions.NotFoundAnimalException;
import business.controllers.AnimalController;
import business.wrapper.AnimalState;
import business.wrapper.AnimalWrapper;
import data.entities.Type;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.ANIMALS)
public class AnimalResource {

    private AnimalController animalController;

    @Autowired
    public void setAnimalController(AnimalController animalController) {
        this.animalController = animalController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalState> showAnimals() {
        return animalController.showAnimals();
    }

    @RequestMapping(value = Uris.NEW, method = RequestMethod.GET)
    public List<AnimalState> showNewAnimals() {
        return animalController.showNewAnimals();
    }

    @RequestMapping(value = Uris.ASSOCIATIONS + Uris.ID, method = RequestMethod.GET)
    public List<AnimalState> showAssociationAnimals(@PathVariable int id) {
        return animalController.showAssociationAnimals(id);
    }

    @RequestMapping(value = Uris.CATS, method = RequestMethod.GET)
    public List<AnimalState> showCats() {
        return animalController.showTypeAnimals(Type.CAT);
    }

    @RequestMapping(value = Uris.DOGS, method = RequestMethod.GET)
    public List<AnimalState> showDogs() {
        return animalController.showTypeAnimals(Type.DOG);
    }

    @RequestMapping(value = Uris.OTHERS, method = RequestMethod.GET)
    public List<AnimalState> showOthers() {
        return animalController.showTypeAnimals(Type.OTHER);
    }

    @RequestMapping(value = Uris.BREED + Uris.ID, method = RequestMethod.GET)
    public List<AnimalState> showBreedAnimals(@PathVariable String breed) {
        return animalController.showBreedAnimals(breed);
    }

    @RequestMapping(value = Uris.ASSOCIATIONS + Uris.ID, method = RequestMethod.POST)
    public void registration(@PathVariable int id, @Valid
    final AnimalWrapper animalWrapper, @RequestParam(value = "image", required = false) MultipartFile[] images)
            throws InvalidAnimalUserEception {
        validateField(animalWrapper.getName(), "name");
        validateField(animalWrapper.getBreed(), "breed");
        if (!this.animalController.registration(id, animalWrapper, images)) {
            throw new InvalidAnimalUserEception();
        }
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws NotFoundAnimalException {
        if (!animalController.deleteAnimal(id)) {
            throw new NotFoundAnimalException();
        }
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public void modify(@RequestBody AnimalWrapper animal, @PathVariable int id) throws NotFoundAnimalException {
        if (!animalController.modifyAnimal(animal, id)) {
            throw new NotFoundAnimalException();
        }
    }

    private void validateField(String field, String msg) throws InvalidAnimalUserEception {
        if (field == null || field.isEmpty()) {
            throw new InvalidAnimalUserEception(msg);
        }
    }

}
