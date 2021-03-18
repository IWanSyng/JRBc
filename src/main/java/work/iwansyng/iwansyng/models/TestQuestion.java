package work.iwansyng.iwansyng.models;

import java.util.ArrayList;
import java.util.List;

public class TestQuestion {


    public String questionText;
    public List<String> answerText;
    public AnswerType answerType;
    public Integer answerValue;

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

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

}
