package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.api.Uris;
import business.wrapper.AssociationDetails;
import business.wrapper.TokenWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.entities.User;

public class LogoutResourceFunctionalTesting {

	@Before
    public void deleteBefore() {
        new RestService().deleteAll();
    }
	
	@Test
    public void testLogout() {
        UserWrapper userWrapper = new UserWrapperBuilder().username("u1").password("u1").build();
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapperBuilder(1).build()).post().build();
        TokenWrapper tokenWrapper = (TokenWrapper) new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.LOGIN).body(userWrapper)
        		.clazz(TokenWrapper.class).post().build();
        new RestBuilder<Object>(RestService.URL).path(Uris.LOGOUT).body(tokenWrapper).post().build();
    }
	
	@After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
