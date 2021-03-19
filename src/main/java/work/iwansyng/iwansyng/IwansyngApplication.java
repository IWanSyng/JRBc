package work.iwansyng.iwansyng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import work.iwansyng.iwansyng.models.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class IwansyngApplication {

	public static void main(String[] args) {
		SpringApplication.run(IwansyngApplication.class, args);
	}
}
