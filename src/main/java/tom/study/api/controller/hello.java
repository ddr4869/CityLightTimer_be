package tom.study.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.response.CommonResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
public class hello {

    private final JwtUtil jwtUtil;

    @GetMapping("hello") // User Test
    public ResponseEntity<Object> hello(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String hello = "hello, " + authentication.getName();
        return CommonResponse.ResponseEntitySuccess(hello);
    }

    @GetMapping("test")
    public String test(HttpServletResponse response) {
        return "test";
    }
}
