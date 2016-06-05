package web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import business.controllers.UserController;
import business.wrapper.UserWrapper;
import data.entities.User;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {
    
    @Autowired
    private UserController userController;
    
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
    
    @RequestMapping("/userRegister")
    public String userRegister(Model model) {
        return "userRegister";
    }
    
    @RequestMapping(value = "/user-register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user", new UserWrapper());
        return "userRegister";
    }

    @RequestMapping(value = "/user-register", method = RequestMethod.POST)
    public String registerUserSubmit(@Valid User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            UserWrapper userWrapper = new UserWrapper(user.getUsername(), user.getEmail(), user.getPassword(), user.getConfirmedPassword(), user.getName(), user.getAddress());
            if (!user.getPassword().equalsIgnoreCase(user.getConfirmedPassword())) {
                bindingResult.rejectValue("confirmedPassword", "error.user", "Las passwords no coinciden");
            }
            else if (userController.registration(userWrapper)) {
                model.addAttribute("username", user.getUsername());
                return "userRegisterSuccess";
            } 
            else {
                    bindingResult.rejectValue("username", "error.user", "Nombre de Usuario o Email ya existe");
            }
        }
        return "userRegister";
    }

}
