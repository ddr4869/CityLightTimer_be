package tom.study.api.controller.light;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/light")
public class LightController {
    private final ReadLightUsecase readLightUsecase;

    @Operation(summary = "신호 잔여 시간 조회", description = "교차로ID를 통해 신호 잔여 시간을 조회합니다. pageNo, numOfRows는 기본값이 1, 1입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = LightFeignMetaResponse.class))),
            @ApiResponse(responseCode = "400", description = "교차로ID(itsId)가 잘못되었을 때 timeout 에러가 발생합니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class)))
    })
    @GetMapping("")
    public ResponseEntity<Object> getLightTimingFeign(
            @RequestParam(name = "itstId") String itstId,
            @RequestParam(name = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(name = "numOfRows", defaultValue = "1") String numOfRows
    ) {
        LightRequest lightRequest = new LightRequest(itstId, pageNo, numOfRows);
        return readLightUsecase.execute(lightRequest);
    }
}
