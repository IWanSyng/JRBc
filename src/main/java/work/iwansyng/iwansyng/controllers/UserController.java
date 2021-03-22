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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String forwardToUserHomePage(User user, Model model) {
        User currentUser = userRepository.findByUsername(user.getUsername());
        if (currentUser.getUsername() != user.getUsername()) {
            return "404";
        }

        model.addAttribute("user", user);

        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping(path = "/{username}")
    public String userHomePage(@PathVariable("username") String username, Model model) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "404";
        }

        model.addAttribute("user", user);

        return "user";
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