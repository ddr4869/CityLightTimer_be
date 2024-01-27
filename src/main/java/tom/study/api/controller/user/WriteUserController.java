package tom.study.api.controller.user;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.api.controller.user.model.LoginUserRequest;
import tom.study.api.controller.user.model.RefreshResponse;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.jwt.redis.JwtRedis;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WriteUserController {
    private final JwtUtil jwtUtil;
    private final JwtRedis jwtRedis;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginUserRequest loginUserRequest) {
        log.info("login test");
        return "login test";
    }

    // TODO : Refresh test
    @PostMapping("/refresh")
    public RefreshResponse refresh(@RequestHeader("Authorization") String header) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String token = jwtUtil.resolveToken(header);
        Map<String, Object> payloads = jwtRedis.getJwtHash(token);
        if (payloads.isEmpty()) {
            throw new JwtException("Exipred or invalid token");
        }
        //jwtRedis.delKey(token);

        RefreshResponse response= new RefreshResponse();
        response.accessToken = jwtUtil.createAccessJwt((String) payloads.get("userName"));
        response.issuer = (String) payloads.get("userName");
        response.issuedAt = (Date) payloads.get("issuedAt");
        response.expired = (Date) payloads.get("expired");
        log.info("access: {}", response);
        return response;
    }
}
