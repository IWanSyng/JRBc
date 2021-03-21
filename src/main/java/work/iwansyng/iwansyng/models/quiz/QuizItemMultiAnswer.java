package work.iwansyng.iwansyng.models.quiz;

public class QuizItemMultiAnswer extends QuizItem {

    public QuizItemMultiAnswer(String questionString, String... answerStrings) {
        super(questionString, answerStrings);
    }

    @Override
    public AnswerType getAnswerType() {
        return AnswerType.MULTIPLE;
    }

    @Override
    public void addAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.size() < answer.getAnswerCandidates().size())
            this.questionAnswerMap.put(answerLineIndex, questionLineIndex);
    }

    @Override
    public void removeAnswer(int answerLineIndex, int questionLineIndex) {
        if (this.questionAnswerMap.containsKey(answerLineIndex))
            this.questionAnswerMap.remove(answerLineIndex, questionLineIndex);
    }
}
