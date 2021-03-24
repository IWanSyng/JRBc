package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.Course;
import work.iwansyng.iwansyng.model.role.Role;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.InstructorRepository;
import work.iwansyng.iwansyng.repository.StudentRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final IwanSyngUserService userService;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

//    @GetMapping(value="/home")
//    public ModelAndView homeUser(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUsername() + "/" + user.getFirstName() + " " + user.getLastName());
//        modelAndView.addObject("userMessage","Content Available Only for Users with User Role");
//        modelAndView.setViewName("user/home");
//
//        return modelAndView;
//    }

    @GetMapping(value="/home")
    public String userHome(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<String> username = getOptionalName(auth.getName());

        if (username.isEmpty()) {
            return "login";
        }

        User user = userService.findUserByUserName(username.get());

        return "redirect:/user/dashboard/" + user.getUsername();
    }

    //@RequestMapping(value = "/dashboard", params = { "id", "Username" }, method = RequestMethod.GET)
    @RequestMapping(value = "/dashboard/{Username}", method = RequestMethod.GET)
    public ModelAndView adminHomePage(@PathVariable("Username") String username, Principal principal) {
        Optional<String> name = getOptionalName(username);
        ModelAndView modelAndView = new ModelAndView();

        if (!name.isPresent()) {
            modelAndView.setViewName("/error");
            return modelAndView;
        }

        User user = userRepository.findByUsername(name.get());

//        modelAndView.addObject("userName", "Welcome " + user.getUsername() + "/" + user.getFirstName() + " " + user.getLastName());
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

        List<Course> courses = courseRepository.findAll();
        Optional<Role> role = Optional.ofNullable(user.getRoles().stream().findFirst().orElse(null));

        modelAndView.addObject("user", user);
        modelAndView.addObject("role", (role.isPresent() ? role.get().getRole() : ""));
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("user/user_dashboard");

        return modelAndView;
    }

    private Optional<String> getOptionalName(String objectName) {
        return Optional.ofNullable(objectName);
    }
}
