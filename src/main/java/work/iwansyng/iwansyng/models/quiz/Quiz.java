package work.iwansyng.iwansyng.models.quiz;

import work.iwansyng.iwansyng.converters.GenericTypeAttributeConverter;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private Long id;

    private String name;
//    private LocalDateTime dateAdded;
//
//    private LocalDateTime dateModified;

    public List<QuizItem> getQuizItems() {
        return quizItems;
    }

    public void setQuizItems(List<QuizItem> quizItems) {
        this.quizItems = quizItems;
    }

    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<QuizItem> quizItems = new ArrayList();

    public Quiz() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public  LocalDateTime getDateAdded() {
//        return dateAdded;
//    }
//
//    public void setDateAdded(LocalDateTime dateAdded) {
//        this.dateAdded = dateAdded;
//    }
//
//    public  LocalDateTime getDateModified() {
//        return dateModified;
//    }

//    public void setDateModified(LocalDateTime dateModified) {
//        this.dateModified = dateModified;
//    }
    public void addQuizItem(QuizItem quizItem) {
        if (!quizItems.contains(quizItem))
            quizItems.add(quizItem);
    }

    public void removeQuizItem(QuizItem quizItem) {
        if (quizItems.contains(quizItem))
            quizItems.remove(quizItem);
    }
}
