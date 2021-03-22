package work.iwansyng.iwansyng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.iwansyng.iwansyng.models.RoleRepository;
import work.iwansyng.iwansyng.models.UserRepository;
import work.iwansyng.iwansyng.models.role.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/login")
    public String login() { return "/login";  }

    @GetMapping
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "404";
        }

        model.addAttribute("user", user.get());

        return "user";
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

    @RequestMapping("/Training/Java")
    public void loginPageRedirect(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Authentication authResult) throws IOException {

        List<String> roles = authResult.getAuthorities()
                .stream()
                .map((g) -> g.getAuthority())
                .collect(Collectors.toList());

        if (roles.contains("ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        }
        else if (roles.contains("USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users

        User user = new User();
        user.setFirstName("Maris");
        user.setLastName("Krastins");
        user.setUsername("student");
        user.setPassword(passwordEncoder.encode("student007"));
        user.setIsEnabled(true);
        user.setRole(roleRepository.findByRoleName("USER"));

        userRepository.save(user);

        user = new User();
        user.setFirstName("Janis");
        user.setLastName("Kalnins");
        user.setUsername("teacher");
        user.setPassword(passwordEncoder.encode("admin1234"));
        user.setIsEnabled(true);
        user.setRole(roleRepository.findByRoleName("ADMIN"));

        userRepository.save(user);

        return userRepository.findAll();
    }

    @GetMapping(path="/two_users")
    public @ResponseBody Iterable<User> getAllUsersOne() {
        // This returns a JSON or XML with the users

        User user = new User();
        user.setFirstName("Regina");
        user.setLastName("Brunevica");
        user.setUsername("u01");
        user.setPassword(passwordEncoder.encode("student008"));
        user.setIsEnabled(true);

        user.setRole(roleRepository.findByRoleName("USER"));
        userRepository.save(user);

        user = new User();
        user.setFirstName("Janis");
        user.setLastName("Zagorskis");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin5678"));
        user.setIsEnabled(true);
        user.setRole(roleRepository.findByRoleName("ADMIN"));

        userRepository.save(user);
        return userRepository.findAll();
    }
}