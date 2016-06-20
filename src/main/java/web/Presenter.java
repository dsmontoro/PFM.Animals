package web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import business.controllers.UserController;
import business.wrapper.UserWrapper;
import data.entities.User;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    @Autowired
    private UserController userController;

    @Autowired
    ServletContext servletContext;

    public Presenter() {
    }

    @ModelAttribute("now")
    public String now() {
        return new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss").format(new Date());
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }
    
    @RequestMapping("/createUser")
    public String createUser(Model model) {
        return "userRegister2";
    }

    @RequestMapping("/userRegister")
    public String userRegister(Model model) {
        return "userRegister";
    }

    @RequestMapping(value = "/user-register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user", new UserWrapper());
        return "userRegister";
    }

    @ResponseBody
    @RequestMapping(value = "/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto() throws IOException {
        InputStream in = servletContext.getResourceAsStream("aa.jpg");
        return IOUtils.toByteArray(in);
    }
    
    @RequestMapping(value = "/user-register", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String registerUserSubmit(@Valid User user, BindingResult bindingResult, Model model,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        if (!image.isEmpty()) {
            try {
                validateImage(image);
            } catch (RuntimeException re) {
                bindingResult.rejectValue("FOTO", "error.user", "Nombre de Usuario o Email ya existe");
            }
        }
        try {
            saveImage("aa" + ".jpg", image);
        } catch (IOException e) {
            bindingResult.rejectValue("FOTO", "error.user", "Nombre de Usuario o Email ya existe");
        }

        if (!bindingResult.hasErrors())

        {
            UserWrapper userWrapper = new UserWrapper(user.getUsername(), user.getSurname(), user.getEmail(), user.getPhone(), user.getAssociation(), user.getAddress(), user.getState(), user.getTown(), user.getPostalCode(), user.getPassword());
            if (!user.getPassword().equalsIgnoreCase(user.getPassword())) {
                bindingResult.rejectValue("confirmedPassword", "error.user", "Las passwords no coinciden");
            } else if (userController.registration(userWrapper)) {
                model.addAttribute("username", user.getUsername());
                return "userRegisterSuccess";
            } else {
                bindingResult.rejectValue("username", "error.user", "Nombre de Usuario o Email ya existe");
            }
        }
        return "userRegister";

    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws RuntimeException, IOException {
        try {
            File file = new File(servletContext.getRealPath("/") + "/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
        } catch (IOException e) {
            throw e;
        }
    }

}
