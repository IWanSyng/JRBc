package work.iwansyng.iwansyng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import work.iwansyng.iwansyng.model.role.User;
import work.iwansyng.iwansyng.repository.ConfigParamRepository;
import work.iwansyng.iwansyng.service.IwanSyngUserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final IwanSyngUserService userService;

    private final ConfigParamRepository configParamRepository;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value= "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
