//package work.iwansyng.iwansyng.model.quiz;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import work.iwansyng.iwansyng.converter.QuizItemAttributeConverter;
//import work.iwansyng.iwansyng.converter.QuizItemListAttributeConverter;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class QuizItemTest {
//
//    private QuizItem quizItem;
//
//    @BeforeEach
//    void setup() {
//        quizItem = new QuizItem();
//    }
//
//    @Test
//    void addAnswerCandidateLine() {
//        String candidateLine0 = "Test text";
//        String candidateLine1 = "Test text2";
//        quizItem.addAnswerCandidateLine(candidateLine0);
//        quizItem.addAnswerCandidateLine(candidateLine1);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(0), candidateLine0);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(1), candidateLine1);
//    }
//
//    @Test
//    void removeAnswerCandidateLineAt() {
//        String candidateLine0 = "Test text";
//        String candidateLine1 = "Test text2";
//        quizItem.addAnswerCandidateLine(candidateLine0);
//        quizItem.addAnswerCandidateLine(candidateLine1);
//
//        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            quizItem.removeAnswerCandidateLineAt(3);
//        });
//
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
//        quizItem.removeAnswerCandidateLineAt(0);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 1);
//        quizItem.removeAnswerCandidateLineAt(0);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 0);
//    }
//
//    @Test
//    void addQuestionLine() {
//        String candidateLine0 = "Test text";
//        String candidateLine1 = "Test text2";
//        quizItem.addQuestionLine(candidateLine0);
//        quizItem.addQuestionLine(candidateLine1);
//        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
//        Assertions.assertEquals(quizItem.getQuestionLines().get(0), candidateLine0);
//        Assertions.assertEquals(quizItem.getQuestionLines().get(1), candidateLine1);
//    }
//
//    @Test
//    void removeQuestionLineAt() {
//        String candidateLine0 = "Test text";
//        String candidateLine1 = "Test text2";
//        quizItem.addQuestionLine(candidateLine0);
//        quizItem.addQuestionLine(candidateLine1);
//
//        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            quizItem.removeQuestionLineAt(3);
//        });
//
//        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
//        quizItem.removeQuestionLineAt(0);
//        Assertions.assertEquals(quizItem.getQuestionLines().size(), 1);
//        quizItem.removeQuestionLineAt(0);
//        Assertions.assertEquals(quizItem.getQuestionLines().size(), 0);
//    }
//
//    @Test
//    void clearAnswers() {
//        quizItem.questionAnswerMap.put("0", "0");
//        quizItem.questionAnswerMap.put("1", "0");
//        quizItem.clearAnswers();
//        Assertions.assertEquals(quizItem.getQuestionAnswerMap().size(), 0);
//    }
//
//    @Test
//    void setQuestionLines() {
//        List<String> candidates = new ArrayList<>();
//        candidates.add("Test text");
//        candidates.add("Test text2");
//        quizItem.setQuestionLines(candidates);
//        Assertions.assertEquals(quizItem.getQuestionLines().size(), 2);
//        Assertions.assertEquals(quizItem.getQuestionLines().get(0), candidates.get(0));
//        Assertions.assertEquals(quizItem.getQuestionLines().get(1), candidates.get(1));
//    }
//
//    @Test
//    void setAnswerCandidateLines() {
//        List<String> candidates = new ArrayList<>();
//        candidates.add("Test text");
//        candidates.add("Test text2");
//        quizItem.setAnswerCandidateLines(candidates);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().size(), 2);
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(0), candidates.get(0));
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines().get(1), candidates.get(1));
//    }
//
//    @Test
//    void setAnswerType() {
//        AnswerType answerType = AnswerType.SINGLE;
//        quizItem.setAnswerType(answerType);
//        Assertions.assertEquals(quizItem.getAnswerType(), answerType);
//    }
//
//    @Test
//    void setQuestionAnswerMap() {
//        Map<String, String> testMap = new HashMap<>();
//        testMap.put("0", "3");
//        testMap.put("1", "2");
//        testMap.put("2", "1");
//        testMap.put("3", "0");
//
//        quizItem.setQuestionAnswerMap(testMap);
//        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), testMap);
//    }
//
//    @Test
//    void getQuestionLines() {
//        setQuestionLines();
//    }
//
//    @Test
//    void getAnswerCandidateLines() {
//        setAnswerCandidateLines();
//    }
//
//    @Test
//    void getAnswerType() {
//        Assertions.assertEquals(quizItem.getAnswerType(), null);
//        setAnswerType();
//
//    }
//
//    @Test
//    void getQuestionAnswerMap() {
//        setQuestionAnswerMap();
//    }
//
//    @Test
//    void testConstructorWithParams() {
//
//        List<String> candidates = new ArrayList<>();
//        candidates.add("Test text");
//        candidates.add("Test text2");
//        candidates.add("Test text3");
//        candidates.add("Test text4");
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            quizItem = new QuizItem(null, candidates.toArray(String[]::new));
//        });
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            quizItem = new QuizItem("Question line", null);
//        });
//
//        quizItem = new QuizItem("This is our question",
//                candidates.toArray(String[]::new));
//
//        Assertions.assertEquals(quizItem.getQuestionLines().get(0),
//                "This is our question");
//
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines(),
//                candidates);
//    }
//
//    @Test
//    void testClone() {
//        QuizItem quizItem =
//                new QuizItem("Test question", "Answer1", "Answer2", "Answer3");
//        quizItem.setAnswerType(AnswerType.SINGLE);
//        quizItem.addAnswer("0", "1");
//        quizItem.addAnswer("1", "0");
//
//        QuizItem clonedItem = quizItem.clone();
//
//        Assertions.assertNotEquals(quizItem.hashCode(), clonedItem.hashCode());
//        Assertions.assertEquals(quizItem.getAnswerType(), clonedItem.getAnswerType());
//        Assertions.assertEquals(quizItem.getQuestionLines(), clonedItem.getQuestionLines());
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines(), clonedItem.getAnswerCandidateLines());
//        Assertions.assertEquals(quizItem.getQuestionAnswerMap(), clonedItem.getQuestionAnswerMap());
//
//    }
//
//    @Test
//    void testConvertFromJsonStringToObject() {
//        QuizItem quizItem =
//                new QuizItem("Test question", "Answer1", "Answer2", "Answer3");
//        quizItem.setAnswerType(AnswerType.SINGLE);
//        quizItem.addAnswer("0", "1");
//        quizItem.addAnswer("1", "0");
//
//        QuizItemAttributeConverter converter = new QuizItemAttributeConverter();
//        String jsonString = converter.convertToDatabaseColumn(quizItem);
//
//        QuizItem convertFromString = converter.convertToEntityAttribute(jsonString);
//
//        Assertions.assertEquals(quizItem.getAnswerType(), convertFromString.getAnswerType());
//        Assertions.assertEquals(quizItem.getQuestionLines(), convertFromString.getQuestionLines());
//        Assertions.assertEquals(quizItem.getAnswerCandidateLines(), convertFromString.getAnswerCandidateLines());
//        Assertions.assertEquals(quizItem.questionAnswerMap, convertFromString.questionAnswerMap);
//    }
//}