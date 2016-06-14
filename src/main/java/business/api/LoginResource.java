package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.LoginController;
import business.wrapper.UserWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.LOGIN)
public class LoginResource {
    
    private LoginController loginController;

    @Autowired
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void login(@RequestBody UserWrapper userWrapper) {
        loginController.login(userWrapper);
    }

}
