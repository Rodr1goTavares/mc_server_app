package mc.com.serverSite.service.auth;


import mc.com.serverSite.api.dto.auth.RegisterCredentialsDTO;
import mc.com.serverSite.entity.user.User;
import org.springframework.http.HttpHeaders;


public interface AuthenticationService<T> {
    User register(RegisterCredentialsDTO registerCredentials);
    HttpHeaders login(T loginCredentials);
}
