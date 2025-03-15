package mc.com.serverSite.service.auth.context;


import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DefaultAuthenticationContext implements AuthenticationContext {

    private final UserRepository userRepository;

    public DefaultAuthenticationContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable("authenticatedUser")
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isNotAuthenticated = authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName().equals("anonymousUser");

        if (isNotAuthenticated) throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        return (User) this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + authentication.getName()));
    }

}
