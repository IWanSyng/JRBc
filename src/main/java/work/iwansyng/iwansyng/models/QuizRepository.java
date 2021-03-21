package work.iwansyng.iwansyng.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.models.quiz.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
