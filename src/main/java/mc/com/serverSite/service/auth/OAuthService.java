package mc.com.serverSite.service.auth;

import mc.com.serverSite.api.dto.auth.RegisterCredentialsDTO;
import mc.com.serverSite.entity.user.User;
import org.springframework.http.HttpHeaders;


public class OAuthService implements AuthenticationService<String> {

    @Override
    public User register(RegisterCredentialsDTO registerCredentials) {
        return null;
    }

    @Override
    public HttpHeaders login(String loginCredentials) {
        return null;
    }

}
