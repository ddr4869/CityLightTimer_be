package tom.study.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.common.config.security.jwt.JwtUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class hello {

    //private final JwtUtil jwtUtil;

    @GetMapping("hello")
    public String hello() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        String token = jwtUtil.createJwt("tom",10000L);
//        log.info("jwtToken: {}", token);
        return "token";
    }

    @GetMapping("/")
    public String getMain1() {
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
}
