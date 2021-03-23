package work.iwansyng.iwansyng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
