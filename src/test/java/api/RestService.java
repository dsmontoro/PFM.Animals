package api;

import business.api.Uris;
import business.wrapper.AnimalWrapper;
import business.wrapper.AnimalWrapperBuilder;
import business.wrapper.TokenWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.entities.Animal;

public class RestService {

    public static final String URL = "http://localhost:8080/PFM.Animals.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        UserWrapper userWrapper = new UserWrapper("admin", "admin@gmail.com", "admin");
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.LOGIN).body(userWrapper).clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public void registerAsociacions(int numAssociations) {
        for (int i = 0; i < numAssociations; i++) {
            UserWrapper user = new UserWrapperBuilder(i).build();
            new RestBuilder<Object>(URL).path(Uris.USERS).body(user).post().build();
        }
        
    }
    
    public void registerAnimals(int numAnimals){
    	for (int i = 0;i < numAnimals;i++){
    		AnimalWrapper animal = new AnimalWrapperBuilder(i).build();
    		new RestBuilder<Object>(URL).path(Uris.ANIMALS).body(animal).post().build();
    	}
    }
    
    public void deleteAnimal(String id){
    	new RestBuilder<Object>(URL).path(Uris.ANIMALS).param(id, id).delete().build();
    }
    
}
