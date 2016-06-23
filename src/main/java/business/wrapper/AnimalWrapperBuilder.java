package business.wrapper;

import java.util.Date;

import data.entities.Type;
import data.entities.User;

public class AnimalWrapperBuilder {

    private AnimalWrapper animalWrapper;

    public AnimalWrapperBuilder(int suffix) {
        User association = new User();
        animalWrapper = new AnimalWrapper("name" + suffix, Type.DOG, "breed" + suffix, association.getId(), new Date(),
                "description" + suffix);
    }

    public AnimalWrapperBuilder() {
        this(0);
    }

    public AnimalWrapperBuilder name(String name) {
        animalWrapper.setName(name);
        return this;
    }

    public AnimalWrapperBuilder type(Type type) {
        animalWrapper.setType(type);
        return this;
    }

    public AnimalWrapperBuilder breed(String breed) {
        animalWrapper.setBreed(breed);
        return this;
    }

    public AnimalWrapperBuilder birthdate(Date birthdate) {
        animalWrapper.setBirthdate(birthdate);
        return this;
    }

    public AnimalWrapperBuilder description(String description) {
        animalWrapper.setDescription(description);
        return this;
    }

    public AnimalWrapper build() {
        return animalWrapper;
    }
}
