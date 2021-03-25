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

    public QuizItem(String questionString, String... answerStrings) {
        if (questionString == null)
            throw new IllegalArgumentException(
                    "'questionString' cannot be null");
        if (answerStrings == null || answerStrings.length == 0)
            throw new IllegalArgumentException(
                    "'answerStrings' cannot be null and 'answerStrings' length should be > 0");

        this.questionLines.add(questionString);
        Collections.addAll(this.answerCandidateLines, answerStrings);
    }

    public void addAnswerCandidateLine(String answerCandidateLine) {
        this.answerCandidateLines.add(answerCandidateLine);
    }

    public void removeAnswerCandidateLineAt(int index) {
        this.answerCandidateLines.remove(index);
    }

    public void addQuestionLine(String questionLine) {
        this.questionLines.add(questionLine);
    }

    public void removeQuestionLineAt(int index) {
        this.questionLines.remove(index);
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
            e.printStackTrace();
        }
        return clone;
    }
}
