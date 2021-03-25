package work.iwansyng.iwansyng.model.quiz;

public class QuizItemSingleAnswer extends QuizItem{

    public QuizItemSingleAnswer() {
        super();
    }

    public QuizItemSingleAnswer(String questionString, String... answerStrings) {
        super(questionString, answerStrings);
        answerType = AnswerType.SINGLE;
    }

    @Override
    public AnswerType getAnswerType() {
        return answerType;
    }

}
