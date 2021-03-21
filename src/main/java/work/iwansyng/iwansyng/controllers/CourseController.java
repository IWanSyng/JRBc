package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.*;
import work.iwansyng.iwansyng.models.role.Instructor;
import work.iwansyng.iwansyng.models.role.Student;
import work.iwansyng.iwansyng.models.role.User;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping(path = "/course")
    public String course(Model model) {

        Course course = new Course();
        course.setCourseName("My first course");
        courseRepository.save(course);

        enrollAllStudents(course);
        assignInstructor(course);

        return "course";
    }

    private void enrollAllStudents(Course course) {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            if(!student.getIsActive())
                continue;

            student.enrollInCourse(course);
            studentRepository.save(student);
        }
    }

    private void assignInstructor(Course course) {
        List<Instructor> instructors = instructorRepository.findAll();
        for (Instructor instructor : instructors) {
            if(!instructor.getIsActive())
                continue;

            instructor.assignCourse(course);
            instructorRepository.save(instructor);
        }
    }
}
