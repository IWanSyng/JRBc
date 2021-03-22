package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import work.iwansyng.iwansyng.models.RoleRepository;
import work.iwansyng.iwansyng.models.StudentRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.User;

import java.util.List;

@Controller
public class UserController {
    // TODO: Login is handled here in this controller

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user_registration")
    public String enrollUser(Model model) {
        model.addAttribute("user", new User());

        return "user_registration";
    }

    @PostMapping(path="/user_registration") // Map ONLY POST Requests
    public String setNewUser(@ModelAttribute("user") User user, Model model) {
        User temp = userRepository.findByUsername(user.getUsername());
        if (temp != null) {
            model.addAttribute("user", temp);
            return "saved";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRoleName("USER"));
        userRepository.save(user);

        return "saved";
    }
}