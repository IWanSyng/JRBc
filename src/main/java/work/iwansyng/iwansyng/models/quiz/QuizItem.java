package work.iwansyng.iwansyng.models.quiz;

import work.iwansyng.iwansyng.converters.GenericTypeAttributeConverter;

import javax.persistence.Convert;
import java.util.*;

public class QuizItem implements Answerable {

    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<String> questionLines = new ArrayList<>();
    @Convert(converter = GenericTypeAttributeConverter.class)
    private List<String> answerCandidateLines = new ArrayList<>();

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    @Convert(converter = GenericTypeAttributeConverter.class)
    AnswerType answerType;

    public void setQuestionAnswerMap(Map<Integer, Integer> questionAnswerMap) {
        this.questionAnswerMap = questionAnswerMap;
    }

    public Map<Integer, Integer> getQuestionAnswerMap() {
        return questionAnswerMap;
    }

    public Map<Integer, Integer> questionAnswerMap = new HashMap<>();


    public Map<Integer, Integer> getAnswerValue() {
        return Map.copyOf(questionAnswerMap);
    }

    public QuizItem() {

    }

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

    public AnswerType getAnswerType() {
        return null;
    }

    @Override
    public void addAnswer(int answerLineIndex, int questionLineIndex) {

    }

    @Override
    public void removeAnswer(int answerLineIndex, int questionLineIndex) {

    }

    public List<String> getQuestionLines() {
        return questionLines;
    }

    public void setQuestionLines(List<String> questionLines) {
        this.questionLines = questionLines;
    }

    public List<String> getAnswerCandidateLines() {
        return answerCandidateLines;
    }

    public void setAnswerCandidateLines(List<String> answerCandidateLines) {
        this.answerCandidateLines = answerCandidateLines;
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

    public void clearAnswer() {
        questionAnswerMap.clear();
    }
}
