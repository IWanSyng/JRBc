package work.iwansyng.iwansyng.model.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizItemSingleAnswerTest {
    private QuizItemSingleAnswer quizItem;

    @BeforeEach
    void setup() {
        quizItem = new QuizItemSingleAnswer();
    }

    @Test
    void getAnswerType() {
        quizItem = new QuizItemSingleAnswer("My question", "answer1", "answer2");
        Assertions.assertEquals(quizItem.getAnswerType(), AnswerType.SINGLE);
    }

    @Test
    void addAnswer() {
        quizItem = new QuizItemSingleAnswer("My question",
                "answer1", "answer2", "answer3");

        quizItem.addAnswer(0, 0);
        quizItem.addAnswer(1, 0);
        quizItem.addAnswer(2, 0);
        Assertions.assertEquals(1, quizItem.getQuestionAnswerMap().size());

    }

    @Test
    void removeAnswer() {
        addAnswer();
        quizItem.removeAnswer(10, 0);
        Assertions.assertEquals(1, quizItem.getQuestionAnswerMap().size());

        quizItem.removeAnswer(2, 0);
        Assertions.assertEquals(0, quizItem.getQuestionAnswerMap().size());
    }
}