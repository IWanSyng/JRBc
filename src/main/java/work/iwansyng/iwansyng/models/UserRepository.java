package work.iwansyng.iwansyng.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.iwansyng.iwansyng.models.role.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByEmail(String email);

    User findByUsername(String userName);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);
}