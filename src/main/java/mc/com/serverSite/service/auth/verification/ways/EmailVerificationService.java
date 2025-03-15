package mc.com.serverSite.service.auth.verification.ways;


import mc.com.serverSite.config.ServerPropertyLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Primary
@Service
public class EmailVerificationService implements VerificationGateway {

    @Value("${account.verification.subject}")
    private String messageSubject;

    private final JavaMailSender mailSender;

    public EmailVerificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerification(String recipient, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(this.messageSubject);
        message.setText("Your activation code: " + otpCode);
        mailSender.send(message);
    }

}
