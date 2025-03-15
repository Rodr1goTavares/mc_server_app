package mc.com.serverSite.service.auth.verification.events;


import org.springframework.context.ApplicationEvent;

public class AccountVerificationEvent extends ApplicationEvent {

    private final String recipient;
    private final String code;

    public AccountVerificationEvent(Object source, String recipient, String code) {
        super(source);
        this.recipient = recipient;
        this.code = code;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public String getCode() {
        return this.code;
    }

}
