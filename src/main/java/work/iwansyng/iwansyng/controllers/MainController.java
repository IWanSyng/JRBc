package work.iwansyng.iwansyng.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ManagedBean;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String home() { return "login";  }

    @GetMapping(path = "/index")
    public String index() { return "login"; }

}