package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.UserController;
import business.wrapper.AssociationDetails;
import business.wrapper.AssociationState;
import data.entities.User;

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
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public AssociationDetails showAssociation(@PathVariable int id) {
        return userController.showAssociation(id);
    }

}
