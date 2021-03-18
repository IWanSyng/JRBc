package work.iwansyng.iwansyng.models;

import java.util.ArrayList;
import java.util.List;


public class Test {
    private Integer id;
    private List<TestQuestion> questions;

    public Test() {
        questions = new ArrayList<>();
    }

    private void addQuestion(TestQuestion testQuestion) {
        questions.add(testQuestion);
    }
}
