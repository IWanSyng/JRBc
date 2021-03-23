package work.iwansyng.iwansyng.model.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.converter.GenericTypeAttributeConverter;
import work.iwansyng.iwansyng.model.*;
import work.iwansyng.iwansyng.model.role.User;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "quizzes")
@Getter @Setter @NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "JSON")
    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<QuizItem> quizItems = new ArrayList<>();


    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleEnd;

    public void addQuizItem(QuizItem quizItem) {
        if (!quizItems.contains(quizItem))
            quizItems.add(quizItem);
    }

    public void removeQuizItem(QuizItem quizItem) {
        quizItems.remove(quizItem);
    }

    public Quiz getQuizWithoutAnswers() {
        Quiz clone = new Quiz();
        List<QuizItem> clonedQuizItems = new ArrayList<>();
        for (int i = 0; i < quizItems.size(); i++) {
            QuizItem clonedItem = quizItems.get(i).clone();
            clonedItem.clearAnswers();
            clonedQuizItems.add(clonedItem);
        }
        clone.id = this.id;
        clone.user = this.user;
        clone.course = this.course;
        clone.name = this.name;
        clone.scheduleStart = this.scheduleStart;
        clone.scheduleEnd = this.scheduleEnd;
        clone.quizItems = clonedQuizItems;
        return clone;
    }
}
