package work.iwansyng.iwansyng.models.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.converters.GenericTypeAttributeConverter;
import work.iwansyng.iwansyng.models.*;
import work.iwansyng.iwansyng.models.role.User;

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

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date(System.currentTimeMillis());

    public void addQuizItem(QuizItem quizItem) {
        if (!quizItems.contains(quizItem))
            quizItems.add(quizItem);
    }

    public void removeQuizItem(QuizItem quizItem) {
        quizItems.remove(quizItem);
    }

    public Quiz getQuizWithoutAnswers() {
        Quiz clone = new Quiz();
        List<QuizItem> clonedQuizItems = List.copyOf(getQuizItems());

        for (int i = 0; i < clonedQuizItems.size(); i++) {
            clonedQuizItems.get(i).clearAnswer();
        }

        clone.id = this.id;
        clone.user = this.user;
        clone.course = this.course;
        clone.name = this.name;
        clone.quizItems = clonedQuizItems;
        return clone;
    }
}
