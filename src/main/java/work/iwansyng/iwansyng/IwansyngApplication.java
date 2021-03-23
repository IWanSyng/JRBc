package work.iwansyng.iwansyng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import work.iwansyng.iwansyng.repository.UserRepository;

@SpringBootApplication
public class IwansyngApplication {

	public static void main(String[] args) {
		SpringApplication.run(IwansyngApplication.class, args);
	}

}
