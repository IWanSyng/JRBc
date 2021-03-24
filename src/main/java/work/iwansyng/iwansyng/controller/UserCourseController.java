package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.*;
import work.iwansyng.iwansyng.model.role.Instructor;
import work.iwansyng.iwansyng.model.role.Student;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.InstructorRepository;
import work.iwansyng.iwansyng.repository.StudentRepository;
import work.iwansyng.iwansyng.repository.UserRepository;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/user/course")
@RequiredArgsConstructor
public class UserCourseController {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;

    @RequestMapping(value = "/enroll/{id}", method = RequestMethod.GET)
    public String userEnrollInCourse(@PathVariable("id") Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<String> authUsername = getOptionalName(auth.getName());

        if (authUsername.isEmpty()) {
            return "error"; // return back to ?
        }

        Optional<Course> course =
                Optional.ofNullable(courseRepository.findCourseById(id).get());

        if (!course.isEmpty()) {
            User user = userRepository.findByUsername(authUsername.get());
            Student s = new Student();

            Course c = course.get();
            Integer noStudents = c.getNumberOfStudents();
            noStudents++;
            c.setNumberOfStudents(noStudents);
            s.setCourse(c);
            s.setUniqueId(noStudents);
            s.setUser(user);

            courseRepository.save(c);
            studentRepository.save(s);
        }

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showCourseById(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<String> authUsername = getOptionalName(auth.getName());
        User user = userRepository.findByUsername(authUsername.get());

        List<Student> students = studentRepository.findAll()
                .stream()
                .filter(s -> s.getCourse().getId().equals(id)
                    && s.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());

        Optional<Course> course =
                Optional.ofNullable(courseRepository.findCourseById(id).get());
        ModelAndView modelAndView = new ModelAndView();

        if (course.isEmpty()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (students.isEmpty()) {
            modelAndView.addObject("flag", true);
        } else {
            modelAndView.addObject("flag", false);
        }

        modelAndView.addObject("course", course.get());
        modelAndView.setViewName("user_course_view");

        return modelAndView;
    }

    private Optional<String> getOptionalName(String objectName) {
        return Optional.ofNullable(objectName);
    }
}
