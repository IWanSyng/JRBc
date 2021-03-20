package work.iwansyng.iwansyng;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.iwansyng.iwansyng.controllers.MainController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IwanSyngWebApplicationTest {
    @Autowired
    private MainController mainController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }
}