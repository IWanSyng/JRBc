package work.iwansyng.iwansyng.models.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.converters.GenericTypeAttributeConverter;
import work.iwansyng.iwansyng.models.*;

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
    private List<QuizItem> quizItems;

    public void addQuizItem(QuizItem quizItem) {
        if (!quizItems.contains(quizItem))
            quizItems.add(quizItem);
    }

    public void removeQuizItem(QuizItem quizItem) {
        quizItems.remove(quizItem);
    }

    public List<QuizItem> getQuizWithoutAnswers() {
        List<QuizItem> quizItems = getQuizItems();
        for (int i = 0; i < quizItems.size(); i++) {
            quizItems.get(i).clearAnswer();
        }
        return quizItems;
    }
}
