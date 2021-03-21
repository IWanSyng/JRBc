package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.StudentRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.Student;
import work.iwansyng.iwansyng.models.role.User;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = "/enroll_as_students")
    public String enrollStudents(Model model) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getRole().getRoleName().equals("USER") || !user.getIsEnabled())
                continue;

            Student student = new Student();
            student.setUser(user);
            studentRepository.save(student);
        }

        return "enroll_as_students";
    }
}
