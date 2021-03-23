package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

@Controller
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final IwanSyngUserService userService;

    @GetMapping(value="/home")
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
