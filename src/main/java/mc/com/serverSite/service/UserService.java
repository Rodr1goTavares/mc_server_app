package mc.com.serverSite.service;

import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Cacheable("users")
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Cacheable("users")
    public List<User> getAllById(List<Long> ids) {
        return this.userRepository.findAllById(ids);
    }

}
