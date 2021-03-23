package work.iwansyng.iwansyng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.model.role.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}