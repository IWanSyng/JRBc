package work.iwansyng.iwansyng;

import java.util.ArrayList;
import java.util.List;

public class TestQuestion {

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public TestQuestion(AnswerType answerType) {
        this.answerType = answerType;
        this.answerText = new ArrayList<>();
    }

    public void addAnswer(String str, boolean isTrue) {
        this.answerText.add(str);
        // if (isTrue) {\
        //answerValue[x] = set
    //}
    }
    public String questionText;
    public List<String> answerText;
    public AnswerType answerType;
    public int answerValue;


}
