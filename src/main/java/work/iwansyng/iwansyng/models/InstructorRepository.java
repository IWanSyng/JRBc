package work.iwansyng.iwansyng.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.models.role.Instructor;
import work.iwansyng.iwansyng.models.role.Student;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}