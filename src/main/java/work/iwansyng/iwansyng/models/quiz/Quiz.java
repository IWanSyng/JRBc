package work.iwansyng.iwansyng.models.quiz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private Long id;

    private String name;
    private LocalDateTime dateAdded;

    private LocalDateTime dateModified;

    public List<QuizItem> getQuizItems() {
        return List.copyOf(quizItems);
    }

    private List<QuizItem> quizItems = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public  LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
    public void addQuizItem(QuizItem quizItem) {
        if (!quizItems.contains(quizItem))
            quizItems.add(quizItem);
    }

    public void removeQuizItem(QuizItem quizItem) {
        if (quizItems.contains(quizItem))
            quizItems.remove(quizItem);
    }
}
