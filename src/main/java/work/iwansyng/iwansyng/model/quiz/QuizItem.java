package work.iwansyng.iwansyng.model.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.converter.GenericTypeAttributeConverter;

import javax.persistence.Convert;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class QuizItem implements Answerable, Cloneable {

    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<String> questionLines = new ArrayList<>();

    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<String> answerCandidateLines = new ArrayList<>();

    @Convert(converter = GenericTypeAttributeConverter.class)
    AnswerType answerType;

    @Convert(converter = GenericTypeAttributeConverter.class)
    protected Map<Integer, Integer> questionAnswerMap = new HashMap<>();

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

    @Override
    public void addAnswer(int answerLineIndex, int questionLineIndex) {
        questionAnswerMap.put(answerLineIndex, questionLineIndex);
    }

    @Override
    public void removeAnswer(int answerLineIndex, int questionLineIndex) {
        questionAnswerMap.remove(answerLineIndex, questionLineIndex);
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
