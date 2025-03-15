package mc.com.serverSite.api.controller.rest.auth;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mc.com.serverSite.api.dto.auth.LoginCredentialsDTO;
import mc.com.serverSite.api.dto.auth.RegisterCredentialsDTO;
import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.service.auth.CredentialsBasedAuthenticationService;
import mc.com.serverSite.service.auth.verification.AccountVerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CredentialsBasedAuthenticationService credentialsBasedAuthenticationService;
    private final AccountVerificationService accountVerificationService;

    public AuthController(
            CredentialsBasedAuthenticationService credentialsBasedAuthenticationService,
            AccountVerificationService accountVerificationService
    ) {
        this.credentialsBasedAuthenticationService = credentialsBasedAuthenticationService;
        this.accountVerificationService = accountVerificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterCredentialsDTO registerData) {
        User registeredUser = this.credentialsBasedAuthenticationService.register(registerData);
        this.accountVerificationService.sendVerification(registeredUser.getEmail());
        return ResponseEntity.created(URI.create("/" + registeredUser.getId()))
                .body("Activation email sent. Verify your email to activate account.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginCredentialsDTO loginData) {
        HttpHeaders loginTokenHeaders = this.credentialsBasedAuthenticationService.login(loginData);
        return ResponseEntity.ok().headers(loginTokenHeaders).body("Login successfully");
    }

}
