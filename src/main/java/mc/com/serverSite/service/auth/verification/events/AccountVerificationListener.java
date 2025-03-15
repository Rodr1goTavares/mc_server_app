package mc.com.serverSite.service.auth.verification.events;

import mc.com.serverSite.service.auth.verification.cache.OtpCache;
import mc.com.serverSite.service.auth.verification.ways.VerificationGateway;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountVerificationListener {

    private final VerificationGateway verificationGateway;
    private final OtpCache otpCache;

    public AccountVerificationListener(VerificationGateway verificationGateway, OtpCache otpCache) {
        this.verificationGateway = verificationGateway;
        this.otpCache = otpCache;
    }

    @EventListener
    public void handleVerificationEvent(AccountVerificationEvent accountVerificationEvent) {
        String recipient = accountVerificationEvent.getRecipient();
        String otpCode = accountVerificationEvent.getCode();
        this.verificationGateway.sendVerification(recipient, otpCode);
        this.otpCache.storeCode(recipient, otpCode);
    }

}
