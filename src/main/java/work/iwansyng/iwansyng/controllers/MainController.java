package work.iwansyng.iwansyng.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import work.iwansyng.iwansyng.models.Instructor;
import work.iwansyng.iwansyng.models.Student;
import work.iwansyng.iwansyng.models.User;
import work.iwansyng.iwansyng.models.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Controller // This means that this class is a Controller
public class MainController {
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/")
    public String home() { return "index";  }

    @GetMapping(path = "/index")
    public String index() { return "index";  }

    @GetMapping(path = "/Training/Java")
    public String trainingJava() { return "index";  }

    @GetMapping(path = "/login")
    public String login() { return "login";  }

    @GetMapping(path = "/hello")
    public String hello() { return "hello";  }

    @PostMapping(path="/add_student", consumes = "application/json", produces = "application/json") // Map ONLY POST Requests
    public String addNewStudent (@RequestParam String firstName, @RequestParam String lastName,
                                                 @RequestParam String userName,
                                                 @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserName(userName);
        student.setPassword(password);
        userRepository.save(student);

        response.setStatus(HttpServletResponse.SC_OK);

        return response.getContentType();
    }

    @PostMapping(path="/add_instructor", consumes = "application/json", produces = "application/json") // Map ONLY POST Requests
    public ResponseEntity<Object> addNewInstructor (@RequestParam String firstName, @RequestParam String lastName,
                                     @RequestParam String userName,
                                     @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setLastName(lastName);
        instructor.setUserName(userName);
        instructor.setPassword(password);
        userRepository.save(instructor);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/instructor/{id}")
                .buildAndExpand(instructor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users

        return userRepository.findAll();
    }
}