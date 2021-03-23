package work.iwansyng.iwansyng.model.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer3;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuizItemTest {

    private QuizItem quizItem;

    @BeforeEach
    void setup() {
        quizItem = new QuizItem();
    }

    @Test
    void addAnswer() {

        Map<Integer, Integer> testMap = new HashMap<>();
        assertEquals(quizItem.getQuestionAnswerMap().size(), 0);
        testMap.put(0, 3);
        quizItem.addAnswer(0, 3);
        assertEquals(quizItem.getQuestionAnswerMap().size(), 1);
        assertTrue(quizItem.getQuestionAnswerMap().containsKey(0));
        assertEquals((int) quizItem.getQuestionAnswerMap().get(0), 3);

        final int iterations = 5;
        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
             int randomKey = 1 + random.nextInt(20);
             int randomValue = random.nextInt(3);
             testMap.put(randomKey, randomValue);
             quizItem.addAnswer(randomKey, randomValue);
        }

        Assertions.assertEquals(testMap.size(), quizItem.getQuestionAnswerMap().size());
        Assertions.assertEquals(testMap, quizItem.getQuestionAnswerMap());
    }

    @Test
    void removeAnswer() {

        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(0, 3);
        testMap.put(1, 2);
        testMap.put(2, 1);
        testMap.put(3, 0);

        quizItem.addAnswer(0, 3);
        quizItem.addAnswer(1, 2);
        quizItem.addAnswer(2, 1);
        quizItem.addAnswer(3, 0);

        Iterator it = testMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            quizItem.removeAnswer((Integer)entry.getKey(), (Integer)entry.getValue());
        }
        assertEquals(quizItem.getQuestionAnswerMap().size(), 0);
    }

    @Test
    void addAnswerCandidateLine() {
        String candidateLine0 = "Test text";
        String candidateLine1 = "Test text2";
        quizItem.addAnswerCandidateLine(candidateLine0);
        quizItem.addAnswerCandidateLine(candidateLine1);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(0), candidateLine0);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(1), candidateLine1);
    }

    @Test
    void removeAnswerCandidateLineAt() {
        String candidateLine0 = "Test text";
        String candidateLine1 = "Test text2";
        quizItem.addAnswerCandidateLine(candidateLine0);
        quizItem.addAnswerCandidateLine(candidateLine1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            quizItem.removeAnswerCandidateLineAt(3);
        });

        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
        quizItem.removeAnswerCandidateLineAt(0);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 1);
        quizItem.removeAnswerCandidateLineAt(0);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 0);
    }

    @Test
    void addQuestionLine() {
        String candidateLine0 = "Test text";
        String candidateLine1 = "Test text2";
        quizItem.addQuestionLine(candidateLine0);
        quizItem.addQuestionLine(candidateLine1);
        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
        Assertions.assertEquals(quizItem.getQuestionLines().get(0), candidateLine0);
        Assertions.assertEquals(quizItem.getQuestionLines().get(1), candidateLine1);
    }

    @Test
    void removeQuestionLineAt() {
        String candidateLine0 = "Test text";
        String candidateLine1 = "Test text2";
        quizItem.addQuestionLine(candidateLine0);
        quizItem.addQuestionLine(candidateLine1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            quizItem.removeQuestionLineAt(3);
        });

        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
        quizItem.removeQuestionLineAt(0);
        Assertions.assertEquals(quizItem.getQuestionLines().size(), 1);
        quizItem.removeQuestionLineAt(0);
        Assertions.assertEquals(quizItem.getQuestionLines().size(), 0);
    }

    @Test
    void clearAnswers() {
        quizItem.addAnswer(0, 0);
        quizItem.addAnswer(1, 0);
        quizItem.clearAnswers();
        Assertions.assertEquals(quizItem.getQuestionAnswerMap().size(), 0);
    }

    @Test
    void setQuestionLines() {
        List<String> candidates = new ArrayList<>();
        candidates.add("Test text");
        candidates.add("Test text2");
        quizItem.setQuestionLines(candidates);
        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
        Assertions.assertEquals(quizItem.getQuestionLines().get(0), candidates.get(0));
        Assertions.assertEquals(quizItem.getQuestionLines().get(1), candidates.get(1));
    }

    @Test
    void setAnswerCandidateLines() {
        List<String> candidates = new ArrayList<>();
        candidates.add("Test text");
        candidates.add("Test text2");
        quizItem.setAnswerCandidateLines(candidates);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(0), candidates.get(0));
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(1), candidates.get(1));
    }

    @Test
    void setAnswerType() {
        AnswerType answerType = AnswerType.SINGLE;
        quizItem.setAnswerType(answerType);
        Assertions.assertEquals(quizItem.getAnswerType(), answerType);
    }

    @Test
    void setQuestionAnswerMap() {
        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(0, 3);
        testMap.put(1, 2);
        testMap.put(2, 1);
        testMap.put(3, 0);

        quizItem.setQuestionAnswerMap(testMap);
        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), testMap);
    }

    @Test
    void getQuestionLines() {
        setQuestionLines();
    }

    @Test
    void getAnswerCandidateLines() {
        setAnswerCandidateLines();
    }

    @Test
    void getAnswerType() {
        Assertions.assertEquals(quizItem.getAnswerType(), null);
        setAnswerType();

    }

    @Test
    void getQuestionAnswerMap() {
        setQuestionAnswerMap();
    }

    @Test
    void testConstructorWithParams() {

        List<String> candidates = new ArrayList<>();
        candidates.add("Test text");
        candidates.add("Test text2");
        candidates.add("Test text3");
        candidates.add("Test text4");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            quizItem = new QuizItem(null, candidates.toArray(String[]::new));
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            quizItem = new QuizItem("Question line", null);
        });

        quizItem = new QuizItem("This is our question",
                candidates.toArray(String[]::new));

        Assertions.assertEquals(quizItem.getQuestionLines().get(0),
                "This is our question");

        Assertions.assertEquals(quizItem.getAnswerCandidateLines(),
                candidates);
    }

    @Test
    void testClone() {
        QuizItem quizItem =
                new QuizItem("Test question", "Answer1", "Answer2", "Answer3");
        quizItem.setAnswerType(AnswerType.SINGLE);
        quizItem.addAnswer(0, 1);
        quizItem.addAnswer(1, 0);

        QuizItem clonedItem = quizItem.clone();

        Assertions.assertNotEquals(quizItem.hashCode(), clonedItem.hashCode());
        Assertions.assertEquals(quizItem.getAnswerType(), clonedItem.getAnswerType());
        Assertions.assertEquals(quizItem.getQuestionLines(), clonedItem.getQuestionLines());
        Assertions.assertEquals(quizItem.getAnswerCandidateLines(), clonedItem.getAnswerCandidateLines());
        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), clonedItem.getQuestionAnswerMap());

    }
}