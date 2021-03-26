package work.iwansyng.iwansyng.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.IwansyngApplication;
import work.iwansyng.iwansyng.model.ConfigParam;
import work.iwansyng.iwansyng.model.role.Role;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.ConfigParamRepository;
import work.iwansyng.iwansyng.service.IwanSyngUserDetailsService;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class LoginControllerTest {

    @Autowired
    private IwanSyngUserService userService;

    @Autowired
    private ConfigParamRepository configParamRepository;

    @Autowired
    private LoginController loginController;

    @Autowired
    private AdminController adminController;

    @Autowired
    private RegistrationController registrationController;

    @BeforeEach
    void setup() {
        loginController = new LoginController(userService, configParamRepository);
    }

    @Test
    void test01Index() {
        Assertions.assertEquals(loginController.index(), "index");
    }

    @Test
    void test02Login() {
        Assertions.assertEquals(loginController.login().getViewName(), "login");
    }

    @Test
    void test03Registration() {
        ConfigParam configParam = configParamRepository.findByConfigParamName("USER_REGISTRATION");
        configParam.setIsEnabled(false);
        configParamRepository.save(configParam);

        ModelAndView modelAndView = registrationController.registration();
        Assertions.assertEquals("error", modelAndView.getViewName());

        configParam.setIsEnabled(true);
        configParamRepository.save(configParam);

        modelAndView = registrationController.registration();
        Assertions.assertEquals("registration", modelAndView.getViewName());
    }

    @Test
    void test04RegistrationAdmin() {
        ModelAndView modelAndView = adminController.registrationAdmin();
        Assertions.assertEquals("/admin/registration_admin", modelAndView.getViewName());
    }
}