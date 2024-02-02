package tom.study.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ReadUserController {
    private final ReadUserUsecase readUserUsecase;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @Operation(summary = "즐겨찾기 목록 조회", description = "즐겨찾기 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
    })
    @GetMapping("/favorites")
    public ResponseEntity<Object> queryFavorites() {
        QueryFavoriteRequest queryFavoriteRequest = new QueryFavoriteRequest();
        return readUserUsecase.execute(queryFavoriteRequest);
    }
}
