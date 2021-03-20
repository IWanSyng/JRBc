package work.iwansyng.iwansyng;

import io.restassured.config.RedirectConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.jupiter.api.*;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import static io.restassured.RestAssured.config;

import org.springframework.test.context.junit4.SpringRunner;
import work.iwansyng.iwansyng.controllers.MainController;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@WebMvcTest(MainController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MainController mainController;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = config()
                .redirect(RedirectConfig.redirectConfig()
                        .followRedirects(false));
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void testWhenAccessAddNewUserWithoutLogin_thenRedirectToLogin() {
        String ROOT_URI = "http://localhost:8080";
        final Response response = RestAssured.get(ROOT_URI + "/hello");

        assertEquals(ROOT_URI + "/login", response.getHeader("Location"));
        assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());
    }

}
