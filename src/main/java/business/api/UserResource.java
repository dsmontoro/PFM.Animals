package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistUserFieldException;
import business.api.exceptions.InvalidUserFieldException;
import business.controllers.UserController;
import business.wrapper.UserState;
import business.wrapper.UserWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.USERS)
public class UserResource {

    private UserController userController;
    
    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void registration(@RequestBody UserWrapper userWrapper) throws InvalidUserFieldException, AlreadyExistUserFieldException {

        validateField(userWrapper.getUsername(), "username");
        validateField(userWrapper.getEmail(), "email");
        validateField(userWrapper.getPassword(), "password");
        if (!this.userController.registration(userWrapper)) {
            throw new AlreadyExistUserFieldException();
        }
    }

    // SUBIDA DE MULTIPLE FICHEROS !CUIDADO!
    /*
     * @RequestMapping(method = RequestMethod.POST, headers = "content-type=multipart/*") public void registrationMultiFile(@Valid final
     * UserWrapper userWrapper, @RequestParam(value = "image", required = false) MultipartFile[] images) throws InvalidUserFieldException,
     * AlreadyExistUserFieldException {
     * 
     * String randomName = "";
     * 
     * if (images != null && images.length > 0) { for (int i = 0; i < images.length; i++) { MultipartFile image = images[i]; if (image !=
     * null && !image.isEmpty()) { try { validateImage(image); } catch (RuntimeException re) { throw new RuntimeException(re.toString()); }
     * try { randomName = randomIdentifier(); saveImage(randomName, image); } catch (IOException e) { throw new RuntimeException(
     * "Only JPG images are accepted"); } } } }
     * 
     * validateField(userWrapper.getUsername(), "username"); validateField(userWrapper.getEmail(), "email");
     * validateField(userWrapper.getPassword(), "password"); validateField(userWrapper.getConfirmedPassword(), "confirmedPassword");
     * validateField(userWrapper.getName(), "name"); userWrapper.setImgName(randomName); if (!this.userController.registration(userWrapper))
     * { throw new AlreadyExistUserFieldException(); } }
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<UserState> showUsers() {
        return userController.showUsers();
    }

    private void validateField(String field, String msg) throws InvalidUserFieldException {
        if (field == null || field.isEmpty()) {
            throw new InvalidUserFieldException(msg);
        }
    }

    

}
