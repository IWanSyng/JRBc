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
}