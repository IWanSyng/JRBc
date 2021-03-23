package work.iwansyng.iwansyng.model.quiz;

public interface Answerable {
    AnswerType getAnswerType();
    void addAnswer(int answerLineIndex, int questionLineIndex);
    void removeAnswer(int answerLineIndex, int questionLineIndex);
}