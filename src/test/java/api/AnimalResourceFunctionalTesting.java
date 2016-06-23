package api;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.AnimalWrapperBuilder;
import data.entities.Animal;
import data.entities.Type;

public class AnimalResourceFunctionalTesting {

RestService restService = new RestService();
    
    @Before
    public void deleteBefore() {
        new RestService().deleteAll();
    }
    
    
    @Test
    public void testCreateAnimals() {
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.ANIMALS).body(new AnimalWrapperBuilder(i).build()).post().build();
        }
    }
    
    @Test
    public void testDeleteAnimals(){
    	for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.ANIMALS).pathId(i+1).delete().build();
        }
    }
    
    @Test
    public void testShowBreed(){
    	List<Animal> animals = Arrays.asList(new RestBuilder<Object>(RestService.URL).path(Uris.ANIMALS).param("breed", "breed1").get().build());
    }
    
    @Test
    public void testShowCats(){
    	List<Animal> animals = Arrays.asList(new RestBuilder<Object>(RestService.URL).path(Uris.ANIMALS).param(Type.CAT).get().build());
    }
}
