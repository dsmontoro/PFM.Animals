package api;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TokenResourceFunctionalTesting {

    @Before
    public void deleteBefore() {
        new RestService().deleteAll();
    }
    
    @Test
    public void testLoginUser() {
        String token = new RestService().registerAndLoginUser();
        assertTrue(token.length() > 20);
        LogManager.getLogger(this.getClass()).info("testLoginUser (token:" + token + ")");
    }
    
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
