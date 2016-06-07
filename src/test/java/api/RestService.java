package api;

import business.api.Uris;
import business.wrapper.TokenWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;

public class RestService {

    public static final String URL = "http://localhost:8080/PFM.Animals.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public String registerAndLoginUser() {
        UserWrapper user = new UserWrapperBuilder().build();
        new RestBuilder<Object>(URL).path(Uris.USERS).body(user).post().build();
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(user.getUsername(), user.getPassword())
                .clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }   
    
    public void registerAsociacions(int numAssociations) {
        for (int i = 0; i < numAssociations; i++) {
            UserWrapper user = new UserWrapperBuilder(i).build();
            new RestBuilder<Object>(URL).path(Uris.USERS).body(user).post().build();
        }
        
    }
    
}
