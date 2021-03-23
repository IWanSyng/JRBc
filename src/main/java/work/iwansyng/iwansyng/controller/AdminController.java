package work.iwansyng.iwansyng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.InstructorRepository;
import work.iwansyng.iwansyng.repository.StudentRepository;
import work.iwansyng.iwansyng.repository.UserRepository;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentRepository studentRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/registration_admin")
    public ModelAndView registrationAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/admin/registration_admin");
        return modelAndView;
    }
}
