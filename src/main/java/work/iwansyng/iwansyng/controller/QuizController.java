package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.Course;
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

    @GetMapping(path = "/quiz_preview")
    public String addQuiz(Model model) {
        Quiz quiz = new Quiz();
        quiz.setName("First quiz");
        Optional<Course> course = courseRepository.findCourseById(3L);
        quiz.setCourse(course.get());

        quiz.setScheduleStart(new Date(System.currentTimeMillis()));
        quiz.setScheduleEnd(new Date(System.currentTimeMillis()));

        QuizItem firstQuestion = new QuizItem(AnswerType.SINGLE, "What is your name?", "Lauris", "Santa", "Maris", "Tom");

        QuizItem secondQuestion = new QuizItem(AnswerType.MULTIPLE, "What is your name?", "Lauris2", "Santa2", "Maris2", "Tom2");
//        secondQuestion.questionAnswerMap.put("Lauris2", "");
//        secondQuestion.questionAnswerMap.put("Santa2", "");
//        secondQuestion.questionAnswerMap.put("Maris2", "");
//        secondQuestion.questionAnswerMap.put("Tom2", "");

        quiz.addQuizItem(firstQuestion);
        quiz.addQuizItem(secondQuestion);
        model.addAttribute("quiz", quiz);

        quizRepository.save(quiz);

        return "quiz_preview";
    }

    // retrieve from the database for admin view
    // all quizzes where the quizzes.course id == Environment course id
    // and quizzes.user id == Principal user id
    @GetMapping(path = "{id}/all")
    public ModelAndView getAllQuizzes(@PathVariable("id") Long id,
                                      Principal principal) {
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

    @PostMapping("/quiz_preview")
    public String submitTest(@ModelAttribute(value = "quiz") Quiz quiz, Model model) {
//        System.out.println(quiz);
        for (QuizItem quizItem : quiz.getQuizItems()) {
            if (quizItem.answerType.equals(AnswerType.SINGLE)) {
                quizItem.questionAnswerMap.put(quizItem.getSubmittedAnswers().get(0), "0");
            }
        }

        quizRepository.save(quiz);

        return "quiz_preview";
    }

//    @GetMapping(path = "/quiz_test", produces= MediaType.APPLICATION_JSON_VALUE)
//    public ModelAndView testQuizController() {
//        List<Quiz> quizzes = quizRepository.findAll()
//                .stream()
//                .filter(q -> q.getCourse().getId().equals(6L))
//                .collect(Collectors.toList());
//
//
//        Quiz quiz = quizzes.get(0);
//        Quiz quizClone = quiz.getQuizWithoutAnswers();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject(quiz);
//        modelAndView.setViewName("quiz_test");
//
//        return modelAndView;
//    }
}
