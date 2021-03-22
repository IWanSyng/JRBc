package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.InstructorRepository;
import work.iwansyng.iwansyng.models.QuizRepository;
import work.iwansyng.iwansyng.models.StudentRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.quiz.*;
import work.iwansyng.iwansyng.models.role.Instructor;
import work.iwansyng.iwansyng.models.role.User;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    UserRepository userRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping(path = "/quiz")
    public String addQuiz(Model model) {
        Quiz quiz = new Quiz();
        quiz.setName("First quiz");

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

        Quiz quiz2 = new Quiz();
        quiz2.setName("Second quiz");

        QuizItem item21 = new QuizItemSingleAnswer("You are tired", "True", "False");
        item21.addAnswer(0, 0);

        quiz2.addQuizItem(item21);

        List<User> users = userRepository.findAll();
         for (User user : users) {
             if (!user.getIsEnabled() || user.getRole().getRoleName().equals("USER"))
                 continue;

             quiz.setUser(user);
             quiz2.setUser(user);

             quizRepository.save(quiz);
             quizRepository.save(quiz2);
         }

        for (User user : users) {
            if (!user.getIsEnabled() || user.getRole().getRoleName().equals("ADMIN"))
                continue;

            quiz.setUser(user);
            quiz2.setUser(user);
            Quiz userQuiz = quiz.getQuizWithoutAnswers();
            Quiz userQuiz2 = quiz2.getQuizWithoutAnswers();
            quizRepository.save(userQuiz);
            quizRepository.save(userQuiz2);
        }
        return "quiz";
    }
}
