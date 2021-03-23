package work.iwansyng.iwansyng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.*;
import work.iwansyng.iwansyng.model.role.Instructor;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.InstructorRepository;
import work.iwansyng.iwansyng.repository.StudentRepository;
import work.iwansyng.iwansyng.repository.UserRepository;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin/course")
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

    /*
        TODO: similar code as above in course method,
          goes in StudentController
    */

    @GetMapping
    public String course() {
        return "redirect:/admin/home";
    }

    @GetMapping(path = "/add_course")
    public ModelAndView setNewCourse(Principal principal, Model model) {
        Optional<String> name = Optional.ofNullable(principal.getName());
        Instructor instructor = null;
        List<Course> courseList = null;
        ModelAndView modelAndView = null;

        if (name.isPresent()) {
            User currentUser = userRepository.findByUsername(name.get());
            instructor = new Instructor();
            instructor.setUser(userRepository.findByUsername(currentUser.getUsername()));
        }
        else {
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/admin/home");

            return modelAndView;
        }

        courseList = new ArrayList<>();

        Course course = new Course();
        course.setCourseName("My first course");
        course.setStartDate(new Date(System.currentTimeMillis()));
        course.setEndDate(new Date(System.currentTimeMillis()));

        instructor.setCourse(course);
        courseList.add(course);
        courseRepository.save(course);

        course = new Course();
        course.setCourseName("My second course");
        course.setStartDate(new Date(System.currentTimeMillis()));
        course.setEndDate(new Date(System.currentTimeMillis()));

        instructor.setCourse(course);
        courseList.add(course);
        courseRepository.save(course);

        instructorRepository.save(instructor);

        modelAndView = new ModelAndView();
        modelAndView.addObject("courses", courseList);
        modelAndView.setViewName("view_course");

        return modelAndView;
    }

    @GetMapping("/course_all")
    public ModelAndView listCourses() {
        List<Course> courses = courseRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("view_course");

        return modelAndView;
    }
}
