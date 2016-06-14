package business.api;

import business.controllers.AnimalController;
import business.wrapper.AnimalWrapper;

public class AnimalControllerMock extends AnimalController{

	private AnimalWrapper animalWrapper = new AnimalWrapper("", "", "", 0);
	
	public AnimalWrapper getUserWrapper() {
        return animalWrapper;
    }

    public void setUserWrapper(AnimalWrapper animalWrapper) {
        this.animalWrapper = animalWrapper;
    }
}
