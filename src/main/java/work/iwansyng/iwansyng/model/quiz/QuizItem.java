package work.iwansyng.iwansyng.model.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class QuizItem implements Answerable, Cloneable {

    private List<String> questionLines = new ArrayList<>();

    private List<String> answerCandidateLines = new ArrayList<>();

    public AnswerType answerType;

    public Map<String, String> questionAnswerMap = new HashMap<>();

    private List<String> submittedAnswers = new ArrayList<>();

    public QuizItem(AnswerType answerType, String questionString, String... answerStrings) {
        if (questionString == null)
            throw new IllegalArgumentException(
                    "'questionString' cannot be null");
        if (answerStrings == null || answerStrings.length == 0)
            throw new IllegalArgumentException(
                    "'answerStrings' cannot be null and 'answerStrings' length should be > 0");

        this.questionLines.add(questionString);
        this.answerType = answerType;
        Collections.addAll(this.answerCandidateLines, answerStrings);
    }

    public void clearAnswers() {
        questionAnswerMap = new HashMap<>();
    }

    @Override
    public QuizItem clone() {
        QuizItem clone = null;
        try {
             clone = (QuizItem) super.clone();
        } catch (CloneNotSupportedException e) {
            clone = new QuizItem(this.answerType, this.questionLines.get(0),
                    this.answerCandidateLines.toArray(String[]::new));
        }
        return clone;
    }
}
