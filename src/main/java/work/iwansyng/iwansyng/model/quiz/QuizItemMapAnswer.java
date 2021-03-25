package work.iwansyng.iwansyng.model.quiz;

public class QuizItemMapAnswer extends QuizItem {

    public QuizItemMapAnswer() {
        super();
    }

    public QuizItemMapAnswer(
            String questionString, String... answerStrings) {
        super(questionString, answerStrings);
        answerType = AnswerType.MAP_MATCH;
    }

    @Override
    public AnswerType getAnswerType() {
        return answerType;
    }
}
