package work.iwansyng.iwansyng.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping(path = "/")
    public String home() { return "login";  }

    @GetMapping(path = "/index")
    public String index() { return "index";  }

    @GetMapping(path = "/Training/Java")
    public String trainingJava() { return "/login";  }

    @GetMapping(path = "/login")
    public String login() { return "/login";  }

    @GetMapping(path = "/admin")
    public String admin() { return "/admin";  }
}