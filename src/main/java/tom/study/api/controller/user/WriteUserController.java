package tom.study.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tom.study.api.controller.user.model.*;
import tom.study.api.usecase.user.WriteUserUsecase;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.jwt.model.CustomJwtClaims;
import tom.study.common.config.security.jwt.redis.JwtRedis;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.response.CommonResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class WriteUserController {
    private final JwtUtil jwtUtil;
    private final JwtRedis jwtRedis;
    private final WriteUserUsecase writeUserUsecase;


    @PostMapping("/refresh")
    @Operation(summary = "토큰 갱신", description = "jwt 토큰을 갱신합니다. Authorization Bearer Token에 refresh token을 넣어주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomJwtClaims.class)))
            ),
            @ApiResponse(responseCode = "401", description = "Invalid Token",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommonResponse.class)))
            )
    })
    public ResponseEntity<Object> refresh(@RequestHeader("Authorization") String header) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("refresh token !");
        String token = jwtUtil.resolveToken(header);
        Map<String, Object> payloads = jwtRedis.getJwtHash(token);
        return CommonResponse.ResponseEntitySuccess(jwtUtil.getCustomClaims(payloads));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody @Valid SignupRequest signupRequest) {
        log.info("sign up !");
        return writeUserUsecase.execute(signupRequest);
    }

    @PostMapping("/empowerment") // ROLE_USER -> ROLE_ADMIN, TODO: 관리자만 요청 보낼 수 있도록 수정
    public ResponseEntity<Object> empowermentUser(@RequestBody @Valid EmpowermentRequest empowermentRequest) {
        return writeUserUsecase.execute(empowermentRequest);
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
