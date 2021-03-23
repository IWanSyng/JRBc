package work.iwansyng.iwansyng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.ConfigParamRepository;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private IwanSyngUserService userService;

    @Autowired
    private ConfigParamRepository configParamRepository;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

//    @GetMapping(value = "/")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("");
//        return modelAndView;
//    }

    @GetMapping(value= "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();

        if (userService.isUserRegistrationEnabled()) {
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        modelAndView.setViewName("error");
        return modelAndView;
    }

    @GetMapping(value="/registration_admin")
    public ModelAndView registrationAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration_admin");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        // TODO: User Optional<User> everywhere!!!
        User userExists = userService.findUserByUserName(user.getUsername());

        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            if(userService.noDefaultAdmin()) {
                userService.saveAdminUser(user);
            } else {
                userService.saveUser(user);
            }
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @PostMapping(value = "/registration_admin")
    public ModelAndView createNewAdminUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUsername());

        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration_admin");
        } else {
            userService.saveAdminUser(user);
            modelAndView.addObject("successMessage", "Admin has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration_admin");

        }

        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView homeAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUsername() + "/" + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");

        return modelAndView;
    }

    @GetMapping(value="/user/home")
    public ModelAndView homeUser(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUsername() + "/" + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("userMessage","Content Available Only for Users with User Role");
        modelAndView.setViewName("user/home");

        return modelAndView;
    }
}
