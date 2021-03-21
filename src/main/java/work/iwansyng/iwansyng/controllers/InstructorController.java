package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.InstructorRepository;
import work.iwansyng.iwansyng.models.StudentRepository;
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
    InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping(path = "/enroll_as_instructors")
    public String enrollInstructors(Model model) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getRole().getRoleName().equals("USER") || !user.getIsEnabled())
                continue;

            Instructor instructor = new Instructor();
            instructor.setUser(user);
            instructorRepository.save(instructor);
        }

        return "enroll_as_instructors";
    }
}
