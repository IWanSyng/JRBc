package work.iwansyng.iwansyng.model.quiz;

public class QuizItemMultiAnswer extends QuizItem {

    public QuizItemMultiAnswer() {
        super();
    }

    public QuizItemMultiAnswer(String questionString, String... answerStrings) {
        super(questionString, answerStrings);
        answerType = AnswerType.MULTIPLE;
    }

    @Override
    public AnswerType getAnswerType() {
        return answerType;
    }
}
