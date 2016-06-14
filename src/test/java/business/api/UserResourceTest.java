package business.api;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;

import business.api.exceptions.AlreadyExistUserFieldException;
import business.api.exceptions.InvalidUserFieldException;
import business.controllers.UserController;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;

public class UserResourceTest {

    private UserResource userResource;

    private UserController userController;

    @Before
    public void before() {
        userController = new UserControllerMock();
        userResource = new UserResource();
        userResource.setUserController(userController);
    }

    @Test
    public void testCreate() throws InvalidUserFieldException, AlreadyExistUserFieldException {
        UserWrapper userWrapper = new UserWrapperBuilder().build();
        userResource.registration(userWrapper,null);
    }

    @Test
    public void testBadRequestCreate() throws AlreadyExistUserFieldException {
        try {
            UserWrapper userWrapper = new UserWrapperBuilder().username("").build();
            userResource.registration(userWrapper,null);
            fail();
        } catch (InvalidUserFieldException exception) {
            LogManager.getLogger(this.getClass()).debug("testBadRequestCreate (" + exception.getMessage() + ")");
        }
    }

    @Test
    public void testRepeatingFieldCreate() throws InvalidUserFieldException, AlreadyExistUserFieldException {
        UserWrapper userWrapper = new UserWrapperBuilder().build();
        userResource.registration(userWrapper,null);
        try {
            userResource.registration(userWrapper,null);
            fail();
        } catch (AlreadyExistUserFieldException exception) {
            LogManager.getLogger(this.getClass()).debug("testRepeatingFieldCreate (" + exception.getMessage() + ")");
        }
    }

}
