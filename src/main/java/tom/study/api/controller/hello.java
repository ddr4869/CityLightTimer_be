package tom.study.api.controller;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.response.ApiResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class hello {

    private final JwtUtil jwtUtil;

//    @GetMapping("hello")
//    public ApiResponse<Object> hello(HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
//        return ApiResponse.ApiResponseSuccess("hello");
//    }

    @GetMapping("hello")
    public ResponseEntity<Object> hello(HttpServletResponse response) {
        return ApiResponse.ResponseEntitySuccess("hello");
    }

    @GetMapping("test")
    public String test(HttpServletResponse response) {
        return "test";
    }
}
