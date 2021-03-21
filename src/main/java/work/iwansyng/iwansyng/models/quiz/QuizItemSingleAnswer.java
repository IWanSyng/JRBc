package work.iwansyng.iwansyng.models.quiz;

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

    @Override
    public void addAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.size() > 0)
            this.questionAnswerMap.clear();

        this.questionAnswerMap.put(answerLineIndex, questionLineIndex);
    }

    @Override
    public void removeAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.containsKey(answerLineIndex))
            this.questionAnswerMap.remove(answerLineIndex, questionLineIndex);
    }
}
