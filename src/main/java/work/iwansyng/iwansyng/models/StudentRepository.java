package work.iwansyng.iwansyng.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.models.quiz.Quiz;
import work.iwansyng.iwansyng.models.role.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}