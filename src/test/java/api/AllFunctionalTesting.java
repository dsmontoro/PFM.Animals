package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    LoginResourceFunctionalTesting.class,
    LogoutResourceFunctionalTesting.class,
    UserResourceFunctionalTesting.class,
})
public class AllFunctionalTesting {

}
