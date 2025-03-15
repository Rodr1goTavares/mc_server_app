package mc.com.serverSite.service.auth.verification.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Component
public class OtpCache {

    private final Cache<String, String> cache;
    private final SecureRandom secureRandom;

    public OtpCache() {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();

        this.secureRandom = new SecureRandom();
    }

    public String generateOtp() {
        return String.format("%06d", this.secureRandom.nextInt(1000000));
    }

    public void storeCode(String recipient, String code) {
        this.cache.put(recipient, code);
    }

    public boolean isValidCode(String recipient, String code) {
        String storedCode = this.cache.getIfPresent(recipient);
        if (storedCode != null && storedCode.equals(code)) {
            this.cache.invalidate(recipient);
            return true;
        }
        return false;
    }

}
