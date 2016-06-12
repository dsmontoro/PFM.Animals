package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.entities.User;

public class UserResourceFunctionalTesting {

    RestService restService = new RestService();
    
    @Before
    public void deleteBefore() {
        new RestService().deleteAll();
    }
    
    
    @Test
    public void testCreate() {
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserWrapperBuilder(i).build()).post().build();
        }
    }

    @Test
    public void testBadRequestCreate() {
        try {
            UserWrapper userWrapper = new UserWrapperBuilder().username("").build();
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testBadRequestCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testRepeatingFieldCreate() {
        UserWrapper userWrapper = new UserWrapperBuilder().build();
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).post().build();
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.CONFLICT, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testRepeatingFieldCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
    
    @Test
    public void testShowAssociations() {
        final int ASSOCIATIONS = 5;
        restService.registerAsociacions(ASSOCIATIONS);;
        List<User> list = Arrays.asList(new RestBuilder<User[]>(RestService.URL).path(Uris.USERS).path(Uris.ASSOCIATIONS).clazz(User[].class).get().build());
        assertEquals(ASSOCIATIONS, list.size());
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }    
    
}
