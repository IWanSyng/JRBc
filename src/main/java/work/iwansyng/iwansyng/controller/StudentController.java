package work.iwansyng.iwansyng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/user/student")
public class StudentController {

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    private final StudentRepository studentRepository;
//
//    public StudentController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @GetMapping
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getStudentById(@PathVariable("id") Long id) {
//
//        return "Get a specific Foo with id=" + id;
//    }
//
//    @GetMapping(path = "/student_enrollment")
//    public String enrollStudent(Model model) {
//        model.addAttribute("user", new User());
//
//        return "student_enrollment";
//    }
//
//    @PostMapping(path="/student_enrollment") // Map ONLY POST Requests
//    public String setNewStudent(@ModelAttribute("user") User user, Model model) {
//        User student = userRepository.findByUsername(user.getUsername());
//        if (student != null) {
//            model.addAttribute("user", student);
//            return "saved";
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(roleRepository.findByRoleName("USER"));
//        userRepository.save(user);
//
//        return "saved";
//    }
}
