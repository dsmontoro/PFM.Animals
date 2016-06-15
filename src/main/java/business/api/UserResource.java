package business.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    ServletContext servletContext;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    
    @ResponseBody
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto(@PathVariable String id) throws IOException {
        //TODO:  Hay que pasar el nombre de la imagen como parametro
        InputStream in = servletContext.getResourceAsStream("/images/" + id +".jpg");
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=multipart/*")
    public void registration(@Valid final UserWrapper userWrapper, @RequestParam(value = "image", required = false) MultipartFile image)
            throws InvalidUserFieldException, AlreadyExistUserFieldException {

        if (image != null && !image.isEmpty()) {
            try {
                validateImage(image);
            } catch (RuntimeException re) {
                throw new RuntimeException(re.toString());
            }
            
            try {
                saveImage(image.getOriginalFilename(), image);
            } catch (IOException e) {
                throw new RuntimeException("Only JPG images are accepted");
            }
        }

        validateField(userWrapper.getUsername(), "username");
        validateField(userWrapper.getEmail(), "email");
        validateField(userWrapper.getPassword(), "password");
        validateField(userWrapper.getConfirmedPassword(), "confirmedPassword");
        validateField(userWrapper.getName(), "name");
        if (!this.userController.registration(userWrapper)) {
            throw new AlreadyExistUserFieldException();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserState> showUsers() {
        return userController.showUsers();
    }

    private void validateField(String field, String msg) throws InvalidUserFieldException {
        if (field == null || field.isEmpty()) {
            throw new InvalidUserFieldException(msg);
        }
    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws RuntimeException, IOException {
        try {
            File file = new File(servletContext.getRealPath("/") + "/images/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
        } catch (IOException e) {
            throw e;
        }
    }

}
