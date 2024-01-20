package tom.study.api.controller;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.common.config.security.jwt.JwtUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class hello {

    private final JwtUtil jwtUtil;

    @GetMapping("hello") // @CookieValue(name = "refresh") Cookie cookie
    public String hello(HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        log.info("hello test");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        log.info("getAuthorities: {}",authentication.getName());
        log.info("getAuthorities: {}",authentication.getAuthorities());
        log.info("getCredentials: {}",authentication.getCredentials());
        return "hello";
    }

    @GetMapping("/")
    public String getMain1(@CookieValue(name = "refresh") Cookie cookie) {
        log.info("Cookie: {}", cookie.getValue());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        log.info("getAuthorities: {}",authentication.getAuthorities());
        log.info("getCredentials: {}",authentication.getCredentials());
        String password = (authentication.getCredentials() == null) ?
                "보안을 위한 eraseCredentialsAfterAuthentication 정책에 의해 성공적으로 null 처리 되었습니다." :
                authentication.getCredentials().toString() + "입니다.";

        return "안녕하세요, " + authentication.getName() + "님!<br>" +
                "귀하의 비밀번호는 " + password;
    }

    @GetMapping("test")
    public String test(HttpServletResponse response) {
        return "test";
    }
}
