package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistUserFieldException;
import business.controllers.UserController;
import business.wrapper.AssociationDetails;
import business.wrapper.AssociationState;
import business.wrapper.UserWrapper;

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
    
    @RequestMapping(value = Uris.DATA, method = RequestMethod.GET)
    public AssociationDetails showAssociation(@RequestParam String tokenValue) {
        return userController.showAssociationData(tokenValue);
    }
    
    @RequestMapping(value = Uris.QUERY + Uris.NAME, method = RequestMethod.GET)
    public List<AssociationState> searchAssociations(@PathVariable String name) {
        return userController.searchAssociations(name);
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.POST)
    public void modifyAssociation(@RequestBody UserWrapper userWrapper, @PathVariable int id) throws AlreadyExistUserFieldException {
    	userController.modifyAssocitaion(userWrapper, id);
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteAssociation(@PathVariable int id) {
    	userController.deleteAssociation(id);
    }

}
