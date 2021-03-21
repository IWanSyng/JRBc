package work.iwansyng.iwansyng.models.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import work.iwansyng.iwansyng.models.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
