package tom.study.common.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tom.study.domain.user.service.UserService;

@RequiredArgsConstructor
@Component
@Slf4j
// 5. AuthenticationManager는 AuthenticationProvider에게 사용자 인증을 위임한다.
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        log.info("authenticate usename: {}", username);
        String password = authentication.getCredentials().toString();
        // 6. Authentication로 제공된 사용자 아이디/PW를 통해 DB로 인증 확인
        // 7. 인증 성공 시 UsernamePasswordAuthenticationToken로 Authentication를 생성하여 AuthenticationManager로 반환한다.
        UserDetails userDetails = userService.loadUserByUsername(username);
        log.info("UsernamePasswordAuthenticationToken start - username: {}", username);
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
