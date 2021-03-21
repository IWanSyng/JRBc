package work.iwansyng.iwansyng.models.quiz;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private List<String> questionTextLines = new ArrayList<>();

    public Question(String questionString) {
        addQuestionLine(questionString);
    }

    public List<String> getQuestionTextLines() {
        // We should return a copy so the contents of the list can't be edited.
        return List.copyOf(questionTextLines);
    }

    public void addQuestionLine(String questionLine) {
        if (!questionTextLines.contains(questionLine))
            questionTextLines.add(questionLine);
    }

    public void removeQuestionLineAt(int index) {
        if (index < 0 || index >= questionTextLines.size())
            throw new IllegalArgumentException("index is out of bounds for 'questionTextLines'");
        questionTextLines.remove(index);
    }
}