package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.converter.GenericTypeAttributeConverter;
import work.iwansyng.iwansyng.repository.CourseRepository;
import work.iwansyng.iwansyng.repository.QuizRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.model.quiz.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @GetMapping(path = "/quiz")
    public String addQuiz(Model model) {
        Quiz quiz = new Quiz();
        quiz.setName("First quiz");
        quiz.setCourse(courseRepository.findCourseById(7L).get());

        QuizItem item1 = new QuizItemSingleAnswer("What is your name", "Santa", "Lauris", "Tom", "Maris");
        item1.addAnswer(2, 0);

        QuizItem item2 = new QuizItemMultiAnswer("What are the most important things in your life?",
                "Alcohol", "Programming", "Music");
        item2.addAnswer(1, 0);
        item2.addAnswer(0, 0);

        QuizItem item3 = new QuizItemMapAnswer("Woman", "White");
        item3.addQuestionLine("Black");
        item3.addQuestionLine("Sour");
        item3.addQuestionLine("Wet");

        item3.addAnswerCandidateLine("Sweet");
        item3.addAnswerCandidateLine("Dry");
        item3.addAnswerCandidateLine("White");

        item3.addAnswer(0, 1);
        item3.addAnswer(1, 2);
        item3.addAnswer(2, 3);
        item3.addAnswer(3, 0);

        quiz.addQuizItem(item1);
        quiz.addQuizItem(item2);
        quiz.addQuizItem(item3);

        quizRepository.save(quiz);

//        List<User> users = userRepository.findAll();
//         for (User user : users) {
//             if (!user.getIsEnabled() || user.getRole().getRoleName().equals("USER"))
//                 continue;
//
//             quiz.setUser(user);
//             quiz2.setUser(user);
//
//             quizRepository.save(quiz);
//             quizRepository.save(quiz2);
//         }
//
//        for (User user : users) {
//            if (!user.getIsEnabled() || user.getRole().getRoleName().equals("ADMIN"))
//                continue;
//
//            quiz.setUser(user);
//            quiz2.setUser(user);
//            Quiz userQuiz = quiz.getQuizWithoutAnswers();
//            Quiz userQuiz2 = quiz2.getQuizWithoutAnswers();
//            quizRepository.save(userQuiz);
//            quizRepository.save(userQuiz2);
//        }
        return "redirect:/quiz/test_quiz";
    }

    @RequestMapping(
            value = "/quiz/test_quiz",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String testQuizController() {
        List<Quiz> quizzes = quizRepository.findAll()
                .stream()
                .filter(q -> q.getCourse().getId().equals(7L))
                .collect(Collectors.toList());

        System.out.println(quizzes.get(0).getName());

        Quiz quiz = quizzes.get(0);
        Quiz quizClone = quiz.getQuizWithoutAnswers();
        //quizClone.setUser(user);

        GenericTypeAttributeConverter genericTypeAttributeConverter = new GenericTypeAttributeConverter();
        String jsonQuiz = genericTypeAttributeConverter.convertToDatabaseColumn(quizClone);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("quizclone", quizClone);
//        modelAndView.setViewName("test_quiz");
//        // MODELANDVIEW object for HTML Page!!! JSONObject and convert HTML!!!

        return jsonQuiz;
    }
}
