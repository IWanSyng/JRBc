package work.iwansyng.iwansyng.models.quiz;

public class QuizItemMapAnswer extends QuizItem {

    public QuizItemMapAnswer() {
        super();
    }

    public QuizItemMapAnswer(String questionString, String... answerStrings) {
        super(questionString, answerStrings);
    }

    @Override
    public AnswerType getAnswerType() {
        return AnswerType.MAP_MATCH;
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
