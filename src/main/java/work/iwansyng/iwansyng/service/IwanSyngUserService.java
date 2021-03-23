package work.iwansyng.iwansyng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.iwansyng.iwansyng.model.ConfigParam;
import work.iwansyng.iwansyng.repository.ConfigParamRepository;
import work.iwansyng.iwansyng.repository.RoleRepository;
import work.iwansyng.iwansyng.repository.UserRepository;
import work.iwansyng.iwansyng.model.role.Role;
import work.iwansyng.iwansyng.model.role.User;

import java.util.*;

@Service
public class IwanSyngUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfigParamRepository configParamRepository;

    @Autowired
    public IwanSyngUserService(UserRepository userRepository,
                               RoleRepository roleRepository,
                               ConfigParamRepository configParamRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.configParamRepository = configParamRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public boolean noDefaultAdmin() {
        List<User> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            return true;
        }
        for (User user : users) {
            if (user.getRoles().contains(roleRepository.findByRole("ADMIN"))) {
                return false;
            }
        }
        return true;
    }

    public boolean isUserRegistrationEnabled() {
        ConfigParam param = configParamRepository.findByConfigParamName("USER_REGISTRATION");
        return param.getIsEnabled();
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsEnabled(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User saveAdminUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}




