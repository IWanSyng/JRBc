package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import work.iwansyng.iwansyng.models.quiz.*;

@Controller
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

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

        quizRepository.save(quiz);
        quizRepository.save(quiz2);

        return "quiz";
    }
}
