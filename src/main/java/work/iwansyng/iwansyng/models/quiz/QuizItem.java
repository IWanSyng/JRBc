package work.iwansyng.iwansyng.models.quiz;

import java.util.HashMap;
import java.util.Map;

public abstract class QuizItem implements Answerable {
    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    protected Question question;
    protected Answer answer;
    protected Map<Integer, Integer> questionAnswerMap = new HashMap<>();


    public Map<Integer, Integer> getAnswerValue() {
        return Map.copyOf(questionAnswerMap);
    }

    public QuizItem(String questionString, String... answerStrings) {
        if (questionString == null)
            throw new IllegalArgumentException(
                    "'questionString' cannot be null");
        if (answerStrings == null || answerStrings.length == 0)
            throw new IllegalArgumentException(
                    "'answerStrings' cannot be null and 'answerStrings' length should be > 0");

        this.question = new Question(questionString);
        this.answer = new Answer(answerStrings);
    }

    public void addQuestionLine(String questionLine) {
        this.question.addQuestionLine(questionLine);
    }

    public void removeQuestionLineAt(int index) {
        this.question.removeQuestionLineAt(index);
    }

    public void addAnswerCandidateLine(String answerCandidateLine) {
        this.answer.addAnswerCandidate(answerCandidateLine);
    }

    public void removeAnswerCandidateLineAt(int index) {
        this.answer.removeAnswerCandidateAt(index);
    }
}
