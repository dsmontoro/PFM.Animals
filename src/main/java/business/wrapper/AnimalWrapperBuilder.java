package business.wrapper;

public class AnimalWrapperBuilder {

	private AnimalWrapper animalWrapper;
	
	public AnimalWrapperBuilder(int suffix){
		animalWrapper = new AnimalWrapper("a" + suffix,"t" + suffix,"r" + suffix,suffix);
	}
	
	public AnimalWrapperBuilder name(String name){
		animalWrapper.setName(name);
		return this;
	}
	
	public AnimalWrapperBuilder tipo(String tipo){
		animalWrapper.setTipo(tipo);
		return this;
	}
	
	public AnimalWrapperBuilder raza(String raza){
		animalWrapper.setRaza(raza);
		return this;
	}
	
	public AnimalWrapper build(){
		return animalWrapper;
	}
}
