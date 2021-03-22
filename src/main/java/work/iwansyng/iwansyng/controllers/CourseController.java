package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.*;
import work.iwansyng.iwansyng.models.role.Instructor;
import work.iwansyng.iwansyng.models.role.Student;
import work.iwansyng.iwansyng.models.role.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InstructorRepository instructorRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping(path = "/course")
    public String course(Model model) {

        Course course = new Course();
        course.setCourseName("My first course");
        course.setStartDate(new Date(System.currentTimeMillis()));
        course.setEndDate(new Date(System.currentTimeMillis()));

        Instructor instructor = new Instructor();
        instructor.setUser(userRepository.findByUsername("teacher"));
        instructor.setCourse(course);

        courseRepository.save(course);
        instructorRepository.save(instructor);

        return "course";
    }
}
/*
    TODO: similar code as above in course method,
          goes in StudentController
 */