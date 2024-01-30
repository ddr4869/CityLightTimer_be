package tom.study.api.controller.user;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.api.controller.user.model.CreateFavoriteRequest;
import tom.study.api.controller.user.model.DeleteFavoriteRequest;
import tom.study.api.controller.user.model.LoginUserRequest;
import tom.study.api.controller.user.model.RefreshResponse;
import tom.study.api.usecase.user.WriteUserUsecase;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.jwt.redis.JwtRedis;
import tom.study.common.response.ApiResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class WriteUserController {
    private final JwtUtil jwtUtil;
    private final JwtRedis jwtRedis;
    private final WriteUserUsecase writeUserUsecase;

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestHeader("Authorization") String header) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String token = jwtUtil.resolveToken(header);
        Map<String, Object> payloads = jwtRedis.getJwtHash(token);
        return ApiResponse.ResponseEntitySuccess(jwtUtil.getCustomClaims(payloads));
    }

    @PostMapping("/favorites/{itstId}")
    public ResponseEntity<Object> addUserFavorites(@PathVariable("itstId") String itstId) {
        CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
        createFavoriteRequest.itstId=itstId;
        return writeUserUsecase.execute(createFavoriteRequest);
    }

    @DeleteMapping("/favorites/{itstId}")
    public ResponseEntity<Object> deleteUserFavorites(@PathVariable("itstId") String itstId) {
        DeleteFavoriteRequest deleteFavoriteRequest = new DeleteFavoriteRequest();
        deleteFavoriteRequest.itstId=itstId;
        return writeUserUsecase.execute(deleteFavoriteRequest);
    }
}
