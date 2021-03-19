package work.iwansyng.iwansyng.models;

import org.springframework.data.jpa.repository.JpaRepository;

import work.iwansyng.iwansyng.models.Message;


public interface MessageRepository extends JpaRepository<Message, Integer> {

}
