package tom.study.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.user.model.QueryFavoriteRequest;
import tom.study.api.usecase.user.ReadUserUsecase;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.feign.resp.LightFeignMetaResponse;
import tom.study.common.response.CommonResponse;
import tom.study.domain.user.model.entity.Favorites;

@Controller // @RestController는 thymeleaf 적용이 안됨
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "user", description = "유저 API")
public class ReadUserController {
    private final ReadUserUsecase readUserUsecase;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @Operation(summary = "즐겨찾기 목록 조회", description = "즐겨찾기 목록을 조회합니다. pageNo, pageSize 기본값이 0, 100입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class))))
    })
    @GetMapping("/favorites")
    public ResponseEntity<Object> queryFavorites(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "100") int pageSize)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return CommonResponse.ResponseEntityUnauthorized("access token is not valid. please login again.");
        }
        QueryFavoriteRequest queryFavoriteRequest = new QueryFavoriteRequest(authentication.getName(), pageNo, pageSize);
        return readUserUsecase.execute(queryFavoriteRequest);
    }
}
