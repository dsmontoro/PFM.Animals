package api;

import org.junit.Before;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;

public class LoginResourceFunctionalTesting {

    RestService restService = new RestService();
    
    @Before
    public void deleteBefore() {
        new RestService().deleteAll();
    }
    
    @Test
    public void testLogin() {
        UserWrapper userWrapper = new UserWrapperBuilder().username("u1").password("u1").build();
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapperBuilder(1).build()).post().build();
        new RestBuilder<Object>(RestService.URL).path(Uris.LOGIN).body(userWrapper).post().build();
    }
}
