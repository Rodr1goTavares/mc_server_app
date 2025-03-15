package mc.com.serverSite.service.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtTokenService {

    @Value("${auth.jwt.secret}")
    private String secret;


    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        return JWT.create()
                .withIssuer("slyoon")
                .withSubject(userDetails.getUsername())
                .withExpiresAt(this.generateTokenExpirationTime())
                .sign(algorithm);
    }

    public String getSubject(String token) {
        return this.verifyToken(token).getSubject();
    }

    private DecodedJWT verifyToken(String token) {
        if (token == null) throw new IllegalArgumentException();
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        return JWT.require(algorithm)
                .withIssuer("slyoon")
                .build()
                .verify(token);
    }

    private Instant generateTokenExpirationTime() {
        return Instant.now().plus(15, ChronoUnit.DAYS);
    }

}
