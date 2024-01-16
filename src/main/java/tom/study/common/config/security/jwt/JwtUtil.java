package tom.study.common.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    public static String secretKey;

    // Plain secretKey encode
    @PostConstruct
    protected void init() {
        log.info("secretKey before: {}", secretKey);
        secretKey= Encoders.BASE64.encode(secretKey.getBytes());
        log.info("secretKey after: {}", secretKey);
    }

    public String createJwt(String username, Long expiredMs) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("createJwt secretKey : {}", secretKey);
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        //SecretKey key = Jwts.SIG.HS256.key().build();

        return Jwts.builder().claim("userName", username)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key)
                .compact();
    }
}
