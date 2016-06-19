package business.api;

import business.controllers.AnimalController;
import business.wrapper.AnimalWrapper;

public class AnimalControllerMock extends AnimalController{

	private AnimalWrapper animalWrapper = new AnimalWrapper("", "", "", 0);
	
	
	public AnimalWrapper getAnimalWrapper() {
        return animalWrapper;
    }

    public void setAnimalWrapper(AnimalWrapper animalWrapper) {
        this.animalWrapper = animalWrapper;
    }
}
