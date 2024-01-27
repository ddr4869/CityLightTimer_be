package tom.study.common.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tom.study.common.config.security.CustomUser;

import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class JwtUtil {
    @Value("${jwt.secret}")
    public String secret;
    @Value("${jwt.expired.access}")
    public Long accessExpired;
    @Value("${jwt.expired.refresh}")
    public Long refreshExpired;

    private SecretKey secretKey;

    // Plain secretKey encode
    @PostConstruct
    protected void init() {
        secret=Encoders.BASE64.encode(secret.getBytes());
        this.secretKey=Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createAccessJwt(String username) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createJwt(username, accessExpired);
    }
    public String createRefreshJwt(String username) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createJwt(username, refreshExpired);
    }

    public String createJwt(String username, Long expired) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .claim("userName", username)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expired))
                .signWith(secretKey)
                .compact();
    }

    // Request Header 에서 토큰 정보 추출
    public String resolveToken(String bearerToken) {
        log.info("bearerToken : {}", bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthenticationFromToken(String token) {
        Claims claims = getPayload(token);
        String username = String.valueOf(claims.get("userName"));
        if (username == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        CustomUser user = new CustomUser(claims.getIssuer(), claims.getIssuer(), "", "SUPER_ADMIN");
        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new JwtException("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            throw new ExpiredJwtException(claims.getHeader(), (Claims) claims, "Invalid JWT Token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new UnsupportedJwtException("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new IllegalArgumentException();
        }
    }
    public Claims getPayload(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return claims.getPayload();
        } catch (RuntimeException e) {
            log.info("Jwt claims something wrong : {}", token);
            throw e;
        }
    }
}
