package work.iwansyng.iwansyng.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ManagedBean;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String home() { return "index";  }

    @GetMapping(path = "/index")
    public String index() { return "index"; }

    @GetMapping(path = "/login")
    public String login() { return "404"; }

}