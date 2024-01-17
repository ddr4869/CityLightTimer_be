package tom.study.common.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class JwtUtil {
    @Value("${jwt.secret}")
    public String secretKey;
    @Value("${jwt.expired.access}")
    public Long accessExpired;
    @Value("${jwt.expired.refresh}")
    public Long refreshExpired;

    // Plain secretKey encode
    @PostConstruct
    protected void init() {
        secretKey= Encoders.BASE64.encode(secretKey.getBytes());
    }

    public String createAccessJwt(String username) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return createJwt(username, accessExpired);
    }
    public String createRefreshJwt(String username) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createJwt(username, refreshExpired);
    }

    public String createJwt(String username, Long expired) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .claim("userName", username)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expired))
                .signWith(key)
                .compact();
    }
}
