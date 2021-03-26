package work.iwansyng.iwansyng.model.quiz;

import org.junit.jupiter.api.*;
import work.iwansyng.iwansyng.converter.QuizItemAttributeConverter;
import work.iwansyng.iwansyng.converter.QuizItemListAttributeConverter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class QuizItemTest {

    private QuizItem quizItem;

    @BeforeEach
    void setup() {
        quizItem = new QuizItem(AnswerType.SINGLE, "Test question", "Answer0");
    }


    @Test
    void test03ClearAnswers() {
        quizItem.questionAnswerMap.put("0", "0");
        quizItem.questionAnswerMap.put("1", "0");
        quizItem.clearAnswers();
        Assertions.assertEquals(quizItem.getQuestionAnswerMap().size(), 0);
    }

    @Test
    void test04SetQuestionLines() {
        List<String> candidates = new ArrayList<>();
        candidates.add(quizItem.getQuestionLines().get(0));
        candidates.add("Test text");
        quizItem.setQuestionLines(candidates);
        Assertions.assertEquals(2, quizItem.getQuestionLines().size());
        Assertions.assertEquals(quizItem.getQuestionLines().get(0), candidates.get(0));
        Assertions.assertEquals(quizItem.getQuestionLines().get(1), candidates.get(1));
    }

    @Test
    void test05SetAnswerCandidateLines() {
        List<String> candidates = new ArrayList<>();
        candidates.add(quizItem.getAnswerCandidateLines().get(0));
        candidates.add("Test text");
        quizItem.setAnswerCandidateLines(candidates);
        Assertions.assertEquals(2, quizItem.getAnswerCandidateLines().size());
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(0), candidates.get(0));
        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(1), candidates.get(1));
    }

    @Test
    void test06SetAnswerType() {
        AnswerType answerType = AnswerType.SINGLE;
        quizItem.setAnswerType(answerType);
        Assertions.assertEquals(quizItem.getAnswerType(), answerType);
    }

    @Test
    void test07SetQuestionAnswerMap() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("0", "3");
        testMap.put("1", "2");
        testMap.put("2", "1");
        testMap.put("3", "0");

        quizItem.setQuestionAnswerMap(testMap);
        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), testMap);
    }

    @Test
    void test08GetQuestionLines() {
        test04SetQuestionLines();
    }

    @Test
    void test09GetAnswerCandidateLines() {
        test05SetAnswerCandidateLines();
    }

    @Test
    void test10GetAnswerType() {
        Assertions.assertNotNull(quizItem.getAnswerType());
    }

    @Test
    void test11GetQuestionAnswerMap() {
        test07SetQuestionAnswerMap();
    }

    @Test
    void test01ConstructorWithParams() {

        List<String> candidates = new ArrayList<>();
        candidates.add("Test text");
        candidates.add("Test text2");
        candidates.add("Test text3");
        candidates.add("Test text4");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            quizItem = new QuizItem(AnswerType.SINGLE,null, candidates.toArray(String[]::new));
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            quizItem = new QuizItem(AnswerType.SINGLE,"Question line", null);
        });

        quizItem = new QuizItem(AnswerType.MULTIPLE,"This is our question",
                candidates.toArray(String[]::new));

        Assertions.assertEquals(quizItem.getQuestionLines().get(0),
                "This is our question");

        Assertions.assertEquals(quizItem.getAnswerCandidateLines(),
                candidates);
    }

    @Test
    void test12Clone() {
        QuizItem quizItem =
                new QuizItem(AnswerType.MULTIPLE, "Test question",
                        "Answer1", "Answer2", "Answer3");
        quizItem.setAnswerType(AnswerType.SINGLE);
        quizItem.questionAnswerMap.put("0", "1");
        quizItem.questionAnswerMap.put("1", "0");

        QuizItem clonedItem = quizItem.clone();

        Assertions.assertNotEquals(quizItem.hashCode(), clonedItem.hashCode());
        Assertions.assertEquals(quizItem.getAnswerType(), clonedItem.getAnswerType());
        Assertions.assertEquals(quizItem.getQuestionLines(), clonedItem.getQuestionLines());
        Assertions.assertEquals(quizItem.getAnswerCandidateLines(), clonedItem.getAnswerCandidateLines());
        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), clonedItem.getQuestionAnswerMap());

        Assertions.assertDoesNotThrow(quizItem::clone);
    }

    @Test
    void test02ConvertFromJsonStringToObject() {
        QuizItem quizItem =
                new QuizItem(AnswerType.SINGLE,"Test question",
                        "Answer1", "Answer2", "Answer3");
        quizItem.setAnswerType(AnswerType.SINGLE);
        quizItem.questionAnswerMap.put("0", "1");
        quizItem.questionAnswerMap.put("1", "0");

        QuizItemAttributeConverter converter = new QuizItemAttributeConverter();
        String jsonString = converter.convertToDatabaseColumn(quizItem);

        QuizItem convertFromString = converter.convertToEntityAttribute(jsonString);

        Assertions.assertEquals(quizItem.getAnswerType(), convertFromString.getAnswerType());
        Assertions.assertEquals(quizItem.getQuestionLines(), convertFromString.getQuestionLines());
        Assertions.assertEquals(quizItem.getAnswerCandidateLines(), convertFromString.getAnswerCandidateLines());
        Assertions.assertEquals(quizItem.questionAnswerMap, convertFromString.questionAnswerMap);
    }
}