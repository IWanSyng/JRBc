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
