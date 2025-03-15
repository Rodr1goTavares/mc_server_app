package mc.com.serverSite.api.controller.rest.auth;

import mc.com.serverSite.service.auth.verification.AccountVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/account/verify")
public class AccountVerificationController {

    private final AccountVerificationService accountVerificationService;

    public AccountVerificationController(AccountVerificationService accountVerificationService) {
        this.accountVerificationService = accountVerificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendCode(@RequestBody Map<String, String> body) {
        this.accountVerificationService.sendVerification(body.get("recipient"));
        return ResponseEntity.ok().body("Verification code sent.");
    }

    @PostMapping("/code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> body) {
        String recipient = body.get("recipient");
        String code = body.get("code");
        this.accountVerificationService.verifyAccount(recipient, code);
        return ResponseEntity.ok().body("Account verified successfully.");
    }

}
