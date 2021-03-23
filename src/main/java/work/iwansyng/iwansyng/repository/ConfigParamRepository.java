package work.iwansyng.iwansyng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.iwansyng.iwansyng.model.ConfigParam;

public interface ConfigParamRepository extends JpaRepository<ConfigParam, Long> {
    ConfigParam findByConfigParamName(String userName);
}
