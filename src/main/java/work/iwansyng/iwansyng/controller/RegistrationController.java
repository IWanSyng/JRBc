package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class RegistrationController {

    private final IwanSyngUserService userService;

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

        if (!bindingResult.hasErrors()) {
            if (userService.noDefaultAdmin()) {
                userService.saveAdminUser(user);
            } else {
                userService.saveUser(user);
            }
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
        }
        modelAndView.setViewName("registration");

        return modelAndView;
    }
}
