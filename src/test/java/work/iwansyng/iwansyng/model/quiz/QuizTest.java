package work.iwansyng.iwansyng.model.quiz;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import work.iwansyng.iwansyng.model.Course;
import work.iwansyng.iwansyng.model.role.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class QuizTest {

    private Quiz quiz;

    @BeforeEach
    void setup() {
        quiz = new Quiz();
    }

    @Test
    void test01AddQuizItem() {

        Random random = new Random();
        int iterations = 1 + random.nextInt(30);

        for (int i = 0; i < iterations; i++) {
            QuizItem mockQuizItem = Mockito.mock(QuizItem.class);
            quiz.addQuizItem(mockQuizItem);
        }
        Assertions.assertEquals(quiz.getQuizItems().size(), iterations);
    }

    @Test
    void test02RemoveQuizItem() {
        QuizItem mockQuizItem1 = Mockito.mock(QuizItem.class);
        QuizItem mockQuizItem2 = Mockito.mock(QuizItem.class);
        quiz.addQuizItem(mockQuizItem1);
        quiz.addQuizItem(mockQuizItem2);

        quiz.removeQuizItem(mockQuizItem1);
        Assertions.assertEquals(quiz.getQuizItems().size(), 1);

        quiz.removeQuizItem(mockQuizItem1);
        Assertions.assertEquals(quiz.getQuizItems().size(), 1);

        quiz.removeQuizItem(mockQuizItem2);
        Assertions.assertEquals(quiz.getQuizItems().size(), 0);
    }

    @Test
    void test03GetQuizWithoutAnswers() {
        QuizItem quizItem = new QuizItem(AnswerType.SINGLE, "blah", "answer0", "answer1");
        quizItem.questionAnswerMap.put("0", "0");

        quiz.addQuizItem(quizItem);

        Date mockDateStart = Mockito.mock(Date.class);
        Date mockDateEnd = Mockito.mock(Date.class);
        Course mockCourse = Mockito.mock(Course.class);
        User mockUser = Mockito.mock(User.class);

        quiz.setScheduleStart(mockDateStart);
        quiz.setScheduleEnd(mockDateEnd);
        quiz.setName("lovely quiz");
        quiz.setCourse(mockCourse);
        quiz.setUser(mockUser);

        Quiz cloneQuiz = quiz.getQuizWithoutAnswers();

        Assertions.assertEquals(quiz.getScheduleStart(), cloneQuiz.getScheduleStart());
        Assertions.assertEquals(quiz.getScheduleEnd(), cloneQuiz.getScheduleEnd());
        Assertions.assertEquals(quiz.getName(), cloneQuiz.getName());
        Assertions.assertEquals(quiz.getCourse(), cloneQuiz.getCourse());
        Assertions.assertEquals(quiz.getUser(), cloneQuiz.getUser());

        for (int i = 0; i < quiz.getQuizItems().size(); i++) {
            Assertions.assertTrue(quiz.getQuizItems().get(i).questionAnswerMap.size() > 0);
            Assertions.assertEquals(cloneQuiz.getQuizItems().get(i).questionAnswerMap.size(), 0);
        }

    }

    @Test
    void test04ScheduleStartDate() {

        Random random = new Random();
        int year = 2000 + random.nextInt(200);
        int month = 3;
        int date = 1 + random.nextInt(31);
        int hours = random.nextInt(24);
        int mins = random.nextInt(60);

        Date simpleDate = null;
        try {
           simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(String.format("%04d-%02d-%02d %02d:%02d", year, month, date, hours, mins));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        quiz.setScheduleStart(simpleDate);

        Assertions.assertEquals(simpleDate, quiz.getScheduleStart());
    }

    @Test
    void test05ScheduleEndDate() {
        Random random = new Random();
        int year = 2000 + random.nextInt(200);
        int month = 3;
        int date = 1 + random.nextInt(31);
        int hours = random.nextInt(24);
        int mins = random.nextInt(60);

        Date simpleDate = null;
        try {
            simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(String.format("%04d-%02d-%02d %02d:%02d", year, month, date, hours, mins));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        quiz.setScheduleEnd(simpleDate);
        Assertions.assertEquals(simpleDate, quiz.getScheduleEnd());
    }

    @Test
    void test06SetName() {
         String mockString = "sdfjsodfjsdfsd";
         quiz.setName(mockString);
        Assertions.assertEquals(mockString, quiz.getName());
    }

    @Test
    void test07SetUser() {
        User mockUser = Mockito.mock(User.class);
        quiz.setUser(mockUser);
        Assertions.assertEquals(mockUser, quiz.getUser());
    }

    @Test
    void test08SetCourse() {
        Course mockCourse = Mockito.mock(Course.class);
        quiz.setCourse(mockCourse);
        Assertions.assertEquals(mockCourse, quiz.getCourse());
    }
}