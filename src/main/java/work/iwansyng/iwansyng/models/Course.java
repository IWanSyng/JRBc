package work.iwansyng.iwansyng.models;

import work.iwansyng.iwansyng.converters.QuizListAttributeConverter;
import work.iwansyng.iwansyng.models.quiz.Quiz;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
//    private LocalDate startDate;
//    private LocalDate endDate;
    private String courseName;

    @Column(columnDefinition = "JSON")
    @Convert(converter = QuizListAttributeConverter.class)
    private List<Quiz> quizzes = new ArrayList<>();

    public Course(String courseName, LocalDate startDate, LocalDate endDate) {
        this.courseName = courseName;
//        this.startDate = startDate;
//        this.endDate = endDate;
    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void removeQuiz(Quiz quiz) {
        quizzes.remove(quiz);
    }
}
