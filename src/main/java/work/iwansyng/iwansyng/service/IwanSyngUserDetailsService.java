package work.iwansyng.iwansyng.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import work.iwansyng.iwansyng.models.User;
import work.iwansyng.iwansyng.models.UserRepository;

import java.util.Optional;

@Service
public class IwanSyngUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public IwanSyngUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(userName));
        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));

        return new IwanSyngUserDetails(user.get());
    }
}
