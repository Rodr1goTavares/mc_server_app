package mc.com.serverSite.service.auth.verification;

import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.service.UserService;
import mc.com.serverSite.service.auth.jwt.JwtTokenService;
import mc.com.serverSite.service.auth.verification.cache.OtpCache;
import mc.com.serverSite.service.auth.verification.events.AccountVerificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountVerificationService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OtpCache otpCache;
    private final UserService userService;

    public AccountVerificationService(
            ApplicationEventPublisher applicationEventPublisher,
            OtpCache otpCache,
            UserService userService,
            JwtTokenService jwtTokenService
    ) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.otpCache = otpCache;
        this.userService = userService;
    }

    @Async
    public void sendVerification(String recipient) {
        String otpCode = this.otpCache.generateOtp();
        AccountVerificationEvent verificationEvent = new AccountVerificationEvent(this, recipient, otpCode);
        applicationEventPublisher.publishEvent(verificationEvent);
    }

    public void verifyAccount(String recipient, String code) {
        if (recipient == null || code == null) throw new IllegalArgumentException();
        if (!this.otpCache.isValidCode(recipient, code)) throw new NoSuchElementException();
        // Todo: Load user by recipient (email or phone)
        User verifiedUser = (User) this.userService.loadUserByEmail(recipient);
        verifiedUser.setActive(true);
        this.userService.updateUser(verifiedUser);
    }

}
