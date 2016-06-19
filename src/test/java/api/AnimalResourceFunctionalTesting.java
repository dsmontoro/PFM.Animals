package api;

import org.junit.Before;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.AnimalWrapperBuilder;

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
}
