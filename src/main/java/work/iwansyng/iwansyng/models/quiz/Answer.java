package work.iwansyng.iwansyng.models.quiz;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private List<String> answerCandidateTextLines = new ArrayList<>();

    public Answer(String... answerStrings) {
        for (String answer : answerStrings) {
            addAnswerCandidate(answer);
        }
    }

    public List<String> getAnswerCandidates() {
        // We should return a copy so the contents of the list can't be edited.
        return List.copyOf(answerCandidateTextLines);
    }

    public void addAnswerCandidate(String answerCandidate) {
        if (!answerCandidateTextLines.contains(answerCandidate))
            answerCandidateTextLines.add(answerCandidate);
    }

    public void removeAnswerCandidateAt(int index) {
        if (index < 0 || index >= answerCandidateTextLines.size())
            throw new IllegalArgumentException("index is out of bounds for 'answerCandidateTextLines'");
        answerCandidateTextLines.remove(index);
    }
}
