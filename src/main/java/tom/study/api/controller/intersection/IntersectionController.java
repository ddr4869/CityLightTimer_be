package tom.study.api.controller.intersection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tom.study.api.controller.intersection.model.NeighborIntersectionRequest;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.api.usecase.intersection.ReadIntersectionUsecase;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.response.CommonResponse;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/intersection")
@Tag(name = "intersection", description = "교차로 API")
@RequiredArgsConstructor
public class IntersectionController {
    private final IntersectionService intersectionService;
    private final ReadIntersectionUsecase readIntersectionUsecase;
    List<IntersectionResponse> intersectionList = new ArrayList<>();
    @Operation(summary = "교차로 리스트 상세 조회", description = "모든 교차로의 상세 정보를 조회합니다. JSON 파일 추출용, Client 호출 X")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionResponse.class))))
    })
    @GetMapping("/list")
    public ResponseEntity<Object> getIntersection() {
        return intersectionService.intersectionInformation();
    }

    @Operation(summary = "교차로 리스트 조회", description = "모든 교차로의 메타데이터를 조회합니다. JSON 파일 추출용, Client 호출 X")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionSimpleResponse.class))))
    })
    @GetMapping("/list/simple")
    public ResponseEntity<Object> getIntersectionSimple() throws IOException {
        return intersectionService.intersectionSimpleInformation();
    }

    // TODO
    @Operation(summary = "인근 교차로 조회", description = "longitude, latitude, distance를 입력받아 인근 교차로를 조회합니다. distance는 단위-m, ex) 1000")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success, 인근 교차로 데이터들이 array 형태로 반환됩니다.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = IntersectionSimpleResponse.class)))),
            @ApiResponse(responseCode = "400", description = "longitude, latitude, distance 중 하나라도 입력되지 않았을 때, 숫자 형식이 아닐 때, 400 에러가 발생합니다.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommonResponse.class))))
            })
    @GetMapping("/list/neighbor")
    public ResponseEntity<Object> getNeighborIntersectionSimple(
            @RequestParam (name = "latitude") Double latitude,
            @RequestParam (name = "longitude") Double longitude,
            @RequestParam (name = "distance") int distance
    ) throws IOException {
        NeighborIntersectionRequest neighborIntersectionRequest = new NeighborIntersectionRequest(latitude, longitude, distance);
        return readIntersectionUsecase.execute(neighborIntersectionRequest);
    }
}
