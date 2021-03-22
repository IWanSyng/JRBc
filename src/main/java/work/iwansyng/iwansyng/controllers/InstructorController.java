package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import work.iwansyng.iwansyng.models.InstructorRepository;
import work.iwansyng.iwansyng.models.RoleRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.Instructor;
import work.iwansyng.iwansyng.models.role.Student;
import work.iwansyng.iwansyng.models.role.User;

import java.util.List;

@Controller
public class InstructorController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping(path = "/instructor_enrollment")
    public String enrollInstructor(Model model) {
        model.addAttribute("user", new User());

        return "instructor_enrollment";
    }

    @PostMapping(path="/instructor_enrollment") // Map ONLY POST Requests
    public String setNewInstructor(@ModelAttribute("user") User user, Model model) {
        User i = userRepository.findByUsername(user.getUsername());
        if (i != null) {
            model.addAttribute("user", i);
            return "saved";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.getRole().setRoleName("USER");
        user.setRole(roleRepository.findByRoleName("ADMIN"));

        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructorRepository.save(instructor);

        return "saved";
    }
}
