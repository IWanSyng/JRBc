package work.iwansyng.iwansyng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.model.role.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}