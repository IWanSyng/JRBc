package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.Course;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.QuizRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.model.quiz.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes("quiz")
@RequestMapping(path = "/quiz")
public class QuizController {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @GetMapping(path = "/quiz_view")
    public String addQuiz(Model model) {

        Quiz quiz = new Quiz();
        quiz.setName("First quiz");
        Optional<Course> course = courseRepository.findCourseById(3L);
        quiz.setCourse(course.get());

        quiz.setScheduleStart(new Date(System.currentTimeMillis()));
        quiz.setScheduleEnd(new Date(System.currentTimeMillis()));

        QuizItem firstQuestion = new QuizItem(AnswerType.SINGLE, "What is your name?", "Lauris", "Santa", "Maris", "Tom");

        QuizItem secondQuestion = new QuizItem(AnswerType.MULTIPLE, "What is your name?", "Lauris2", "Santa2", "Maris2", "Tom2");

        quiz.addQuizItem(firstQuestion);
        quiz.addQuizItem(secondQuestion);
        model.addAttribute("quiz", quiz);

        quizRepository.save(quiz);

        return "quiz_view";
    }

    @GetMapping(path = "admin/{id}/view")
    public ModelAndView getAdminQuizView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Quiz> quiz = Optional.ofNullable(quizRepository.findById(id).get());

        if (quiz.isEmpty()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.addObject("quiz", quiz.get());
        modelAndView.setViewName("admin/quiz_view");

        return modelAndView;
    }

    @GetMapping(path = "user/{id}/view")
    public ModelAndView getUserQuizView(@PathVariable("id") Long id, Principal principal) {
        // Course id comes in here
        ModelAndView modelAndView = new ModelAndView();
        Optional<String> currentUser = Optional.ofNullable(principal.getName());
        User user = userRepository.findByUsername(currentUser.get());
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(id).get());

        List<Quiz> quizList = quizRepository.findAll()
                .stream()
                .filter(q -> q.getCourse().getId().equals(course.get().getId()))
                .filter(q -> q.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());

        if (quizList.isEmpty()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.addObject("quiz", quizList.get(0));
        modelAndView.setViewName("user/quiz_view");

        return modelAndView;
    }

    // retrieve from the database for admin view
    // all quizzes where the quizzes.course id == Environment course id
    // and quizzes.user id == Principal user id
    @GetMapping(path = "{id}/results")
    public ModelAndView getQuizResults(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Course> course = courseRepository.findCourseById(id);

        List<Quiz> quizList = quizRepository.findAll()
                .stream()
                .filter(q -> q.getCourse().getId() == course.get().getId())
                .collect(Collectors.toList());

        modelAndView.addObject("quizList", quizList);
        modelAndView.setViewName("quizzes");

        return modelAndView;
    }

    @GetMapping(path = "{id}/all")
    public ModelAndView getAllQuizzes(@PathVariable("id") Long id,
                                      Principal principal) {
        Optional<String> currentUser = Optional.ofNullable(principal.getName());
        User user = userRepository.findByUsername(currentUser.get());
        ModelAndView modelAndView = new ModelAndView();
        Optional<Course> course = courseRepository.findCourseById(id);

        List<Quiz> quizList = quizRepository.findAll()
                .stream()
                .filter(q -> q.getCourse().getId().equals(course.get().getId()))
                .filter(q -> q.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());

        modelAndView.addObject("quizList", quizList);
        modelAndView.setViewName("quizzes");

        return modelAndView;
    }

    @PostMapping("/quiz_view")
    public String submitTest(@ModelAttribute(value = "quiz") Quiz quiz, Model model) {
//        System.out.println(quiz);
        for (QuizItem quizItem : quiz.getQuizItems()) {
            if (quizItem.answerType.equals(AnswerType.SINGLE)) {
                quizItem.questionAnswerMap.put(quizItem.getSubmittedAnswers().get(0), "0");
            }
        }

        quizRepository.save(quiz);

        return "quiz_view";
    }
}
