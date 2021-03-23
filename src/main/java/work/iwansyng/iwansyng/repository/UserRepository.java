package work.iwansyng.iwansyng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.model.role.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}