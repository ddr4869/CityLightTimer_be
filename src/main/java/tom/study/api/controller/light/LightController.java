package tom.study.api.controller.light;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.api.usecase.light.ReadLightUsecase;
import tom.study.common.feign.resp.LightFeignMetaResponse;
import tom.study.common.feign.resp.LightFeignResponse;
import tom.study.common.response.CommonResponse;
import tom.study.common.response.swaggerResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "light", description = "신호 API")
@RequestMapping("/api/light")
public class LightController {
    private final ReadLightUsecase readLightUsecase;

    @Operation(summary = "신호 잔여 시간 조회", description = "교차로ID를 통해 신호 잔여 시간을 조회합니다. pageNo, pageSize 기본값이 0, 1입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommonResponse.class)))),
            @ApiResponse(responseCode = "400", description = "교차로ID(itsId)가 잘못되었을 때 timeout 에러가 발생합니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class)))
    })
    @GetMapping("")
    public ResponseEntity<Object> getLightTimingFeign(
            @RequestParam(name = "itstId") String itstId,
            @RequestParam(name = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(name = "pageSize", defaultValue = "1") String pageSize
    ) {
        LightRequest lightRequest = new LightRequest(itstId, pageNo, pageSize);
        return readLightUsecase.execute(lightRequest);
    }
}
