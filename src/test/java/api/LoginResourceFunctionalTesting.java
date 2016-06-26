package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.api.exceptions.InvalidLoginException;
import business.wrapper.TokenWrapper;
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
    	new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapperBuilder(1).build()).post().build();
    	UserWrapper userWrapper = new UserWrapperBuilder().username("u1").password("u1").build();
    	TokenWrapper tokenWrapper = (TokenWrapper) new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.LOGIN).body(userWrapper)
        		.clazz(TokenWrapper.class).post().build();
        assertTrue(tokenWrapper.getToken().length() >= 20);
    }
    
    public void testIncorrectLogin() {
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapperBuilder(1).build()).post().build();
        UserWrapper userWrapper = new UserWrapperBuilder().username("u1").password("u2").build();
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.LOGIN).body(userWrapper)
                    .clazz(TokenWrapper.class).post().build();
        fail();
    }
    
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
