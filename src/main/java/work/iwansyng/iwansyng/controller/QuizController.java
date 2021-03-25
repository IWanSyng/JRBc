package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.iwansyng.iwansyng.model.Course;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.QuizRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.model.quiz.*;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@SessionAttributes("quiz")
public class QuizController {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @GetMapping(path = "/quiz_preview")
    public String addQuiz(Model model) {
        Quiz quiz = new Quiz();
        quiz.setName("First quiz");
        quiz.setCourse(new Course());

        quiz.setScheduleStart(new Date(System.currentTimeMillis()));
        quiz.setScheduleEnd(new Date(System.currentTimeMillis()));

        QuizItem firstQuestion = new QuizItemSingleAnswer("What is your name?", "Lauris", "Santa", "Maris", "Tom");
        firstQuestion.questionAnswerMap.put("Lauris", "");
        firstQuestion.questionAnswerMap.put("Santa", "");
        firstQuestion.questionAnswerMap.put("Maris", "");
        firstQuestion.questionAnswerMap.put("Tom", "");

        QuizItem secondQuestion = new QuizItemMultiAnswer("What is your name?", "Lauris2", "Santa2", "Maris2", "Tom2");
        secondQuestion.questionAnswerMap.put("Lauris2", "");
        secondQuestion.questionAnswerMap.put("Santa2", "");
        secondQuestion.questionAnswerMap.put("Maris2", "");
        secondQuestion.questionAnswerMap.put("Tom2", "");

        quiz.addQuizItem(firstQuestion);
        quiz.addQuizItem(secondQuestion);
        model.addAttribute("quiz", quiz);


//        quizRepository.save(quiz);

        return "quiz_preview";
    }

    @PostMapping("/quiz_preview")
    public String submitTest(@ModelAttribute(value = "quiz") Quiz quiz, Model model) {
        System.out.println(quiz);
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
