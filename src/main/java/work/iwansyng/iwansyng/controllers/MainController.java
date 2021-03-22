package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.iwansyng.iwansyng.models.RoleRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.User;

import javax.annotation.ManagedBean;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String home() { return "index";  }

    @GetMapping(path = "/index")
    public String index() { return "index"; }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = "/user_registration")
    public String enrollUser(Model model) {
        model.addAttribute("user", new User());

        return "user_registration";
    }

    @PostMapping(path = "/user_registration")
    public String saveEnrolledUser(User user, Model model) {
        user.setIsEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRoleName("USER"));
        userRepository.save(user);

        model.addAttribute("user", user);

        return "redirect:/user/" + user.getUsername();
    }

}