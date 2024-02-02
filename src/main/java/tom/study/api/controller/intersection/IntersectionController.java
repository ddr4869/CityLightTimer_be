package tom.study.api.controller.intersection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.response.CommonResponse;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/intersection")
@RequiredArgsConstructor
public class IntersectionController {
    private final IntersectionService intersectionService;
    List<IntersectionResponse> intersectionList = new ArrayList<>();
    @Operation(summary = "교차로 리스트 상세 조회", description = "모든 교차로의 상세 정보를 조회합니다. JSON 파일 추출용, Client 호출 X")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionResponse.class))))
    })
    @GetMapping("/list")
    public ResponseEntity<Object> getIntersectionList() {
        return intersectionService.intersectionInformation();
    }

    @Operation(summary = "교차로 리스트 조회", description = "모든 교차로의 메타데이터를 조회합니다. JSON 파일 추출용, Client 호출 X")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionSimpleResponse.class))))
    })
    @GetMapping("/list/simple")
    public ResponseEntity<Object> getIntersectionSimpleList() throws IOException {
        return intersectionService.intersectionSimpleInformation();
    }

    // TODO
    @Operation(summary = "인근 교차로 조회", description = "인근 교차로의 메타데이터 리스트를 조회합니다. !! TODO: 아직 개발 안됨 !!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionSimpleResponse.class))))
            })
    @GetMapping("/list/neighbor")
    public ResponseEntity<Object> getNeighborIntersectionSimple(LightRequest lightRequest) throws IOException {
        return intersectionService.intersectionSimpleInformation();
    }
}
