package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.UserController;
import business.wrapper.AssociationState;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.USERS + Uris.ASSOCIATIONS)
public class AssociationResource {
    
    private UserController userController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AssociationState> showAssociations() {
        return userController.showAssociations();
    }

}
