package work.iwansyng.iwansyng.model.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizItemMapAnswerTest {

    private QuizItemMapAnswer quizItem;

    @BeforeEach
    void setup() {
        quizItem = new QuizItemMapAnswer();
    }
    @Test
    void getAnswerType() {
        quizItem = new QuizItemMapAnswer("My question", "answer1", "answer2");
        Assertions.assertEquals(quizItem.getAnswerType(), AnswerType.MAP_MATCH);
    }

    @Test
    void addAnswer() {
        quizItem = new QuizItemMapAnswer("My question",
                "answer1", "answer2", "answer3");

        quizItem.addAnswer(0, 0);
        quizItem.addAnswer(1, 0);
        quizItem.addAnswer(2, 0);
        Assertions.assertEquals(3, quizItem.getQuestionAnswerMap().size());
        quizItem.addAnswer(3,0);
        Assertions.assertEquals(3, quizItem.getQuestionAnswerMap().size());
    }

    @Test
    void removeAnswer() {
        addAnswer();
        quizItem.removeAnswer(1, 0);
        Assertions.assertEquals(2, quizItem.getQuestionAnswerMap().size());
        quizItem.removeAnswer(3,0);
        Assertions.assertEquals(2, quizItem.getQuestionAnswerMap().size());
    }
}