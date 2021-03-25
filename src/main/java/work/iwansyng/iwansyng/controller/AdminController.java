package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.Course;
import work.iwansyng.iwansyng.model.role.Role;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.InstructorRepository;
import work.iwansyng.iwansyng.repository.StudentRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final IwanSyngUserService userService;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @GetMapping(value="/home")
    public String adminHome(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<String> username = getOptionalName(auth.getName());

        if (username.isEmpty()) {
            return "login";
        }

        User user = userService.findUserByUserName(username.get());

        return "redirect:/admin/dashboard/" + user.getUsername();
    }

    //@RequestMapping(value = "/dashboard", params = { "id", "Username" }, method = RequestMethod.GET)
    @RequestMapping(value = "/dashboard/{Username}", method = RequestMethod.GET)
    public ModelAndView adminHomePage(@PathVariable("Username") String username, Principal principal) {
        Optional<String> name = getOptionalName(username);
        User user = null;
        ModelAndView modelAndView = null;

        if (!name.isPresent()) {
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/error");
            return modelAndView;
        }

        user = userRepository.findByUsername(name.get());

        modelAndView = new ModelAndView();

        List<Course> courses = courseRepository.findAll();
        Optional<Role> role = Optional.ofNullable(user.getRoles().stream().findFirst().orElse(null));

        modelAndView.addObject("user", user);
        modelAndView.addObject("role", (role.isPresent() ? role.get().getRole() : ""));
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("admin/admin_dashboard");

        return modelAndView;
    }

    @GetMapping(value = "/create_new_course")
    public ModelAndView createNewCourse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create_new_course");

        return modelAndView;
    }

    @GetMapping(value="/registration_admin")
    public ModelAndView registrationAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/admin/registration_admin");
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

        if (!bindingResult.hasErrors()) {
            userService.saveAdminUser(user);
            modelAndView.addObject("successMessage", "Admin has been registered successfully");
            modelAndView.addObject("user", new User());

        }
        modelAndView.setViewName("/admin/registration_admin");

        return modelAndView;
    }

    private Optional<String> getOptionalName(String objectName) {
        return Optional.ofNullable(objectName);
    }
}
