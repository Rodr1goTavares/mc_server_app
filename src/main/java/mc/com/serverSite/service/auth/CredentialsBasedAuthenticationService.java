package mc.com.serverSite.service.auth;

import mc.com.serverSite.api.dto.auth.LoginCredentialsDTO;
import mc.com.serverSite.api.dto.auth.RegisterCredentialsDTO;
import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.exceptions.EmailAlreadyExistsException;
import mc.com.serverSite.exceptions.UsernameAlreadyExistsException;
import mc.com.serverSite.repository.UserRepository;
import mc.com.serverSite.service.auth.jwt.JwtTokenService;
import mc.com.serverSite.service.auth.verification.AccountVerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CredentialsBasedAuthenticationService implements AuthenticationService<LoginCredentialsDTO> {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final AccountVerificationService accountVerificationService;

    public CredentialsBasedAuthenticationService(
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService,
            UserRepository userRepository,
            AccountVerificationService accountVerificationService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.accountVerificationService = accountVerificationService;
    }

    @Override
    public User register(RegisterCredentialsDTO registerData) {
        if (this.userRepository.findByEmail(registerData.email()).isPresent())
            throw new EmailAlreadyExistsException("Email already registered.");
        if (this.userRepository.findByUsername(registerData.username()).isPresent())
            throw new UsernameAlreadyExistsException("Username already exists.");
        String encodedPassword = new BCryptPasswordEncoder().encode(registerData.password());
        User user = new User(
                registerData.username(),
                registerData.email(),
                encodedPassword
        );
        return this.userRepository.save(user);
    }

    @Override
    public HttpHeaders login(LoginCredentialsDTO loginData) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginData.username(),
                loginData.password()
        );
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();

        // TEST
        /*
        if (!user.isActive()) {
            this.accountVerificationService.sendVerification(user.getEmail());
            throw new DisabledException("Account is not active. A verification code has been sent to the account email.");
        }
        */
        String token = this.jwtTokenService.generateToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

}
