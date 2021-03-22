package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.iwansyng.iwansyng.models.RoleRepository;
import work.iwansyng.iwansyng.models.StudentRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.User;

@Controller
@RequestMapping(value="/student")
public class StudentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudentById(@PathVariable("id") Long id) {

        return "Get a specific Foo with id=" + id;
    }

    @GetMapping(path = "/student_enrollment")
    public String enrollStudent(Model model) {
        model.addAttribute("user", new User());

        return "student_enrollment";
    }

    @PostMapping(path="/student_enrollment") // Map ONLY POST Requests
    public String setNewStudent(@ModelAttribute("user") User user, Model model) {
        User student = userRepository.findByUsername(user.getUsername());
        if (student != null) {
            model.addAttribute("user", student);
            return "saved";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRole(roleRepository.findByRoleName("USER"));
        userRepository.save(user);

        return "saved";
    }
}
