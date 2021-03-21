package work.iwansyng.iwansyng.models.quiz;

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

    @Override
    public void addAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.size() < getAnswerCandidateLines().size())
            this.questionAnswerMap.put(answerLineIndex, questionLineIndex);
    }

    @Override
    public void removeAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.containsKey(answerLineIndex))
            this.questionAnswerMap.remove(answerLineIndex, questionLineIndex);
    }
}
