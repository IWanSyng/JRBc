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
@RequestMapping(path = "/user/course")
@RequiredArgsConstructor
public class UserCourseController {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;

    /*
        TODO: similar code as above in course method,
          goes in StudentController
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showCourseById(@PathVariable("id") Long id) {
        Optional<Course> course =
                Optional.ofNullable(courseRepository.findCourseById(id).get());
        ModelAndView modelAndView = new ModelAndView();

        if (course.isEmpty()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.addObject("course", course.get());
        modelAndView.setViewName("user_course_view");

        return modelAndView;
    }
}
