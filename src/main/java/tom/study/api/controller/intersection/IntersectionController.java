package tom.study.api.controller.intersection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.response.ApiResponse;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/intersection")
@RequiredArgsConstructor
public class IntersectionController {
    private final IntersectionService intersectionService;
    @GetMapping("/list")
    public ApiResponse<Object> getIntersectionList() {
        return ApiResponse.ApiResponseSuccess(intersectionService.intersectionInformation());
    }

    @GetMapping("/list/simple")
    public ApiResponse<Object> getIntersectionSimpleList() throws IOException {
        return ApiResponse.ApiResponseSuccess(intersectionService.intersectionSimpleInformation());
    }

    // TODO
    @GetMapping("/list/neighbor")
    public ApiResponse<Object> getNeighborIntersectionSimple(LightRequest lightRequest) throws IOException {
        return ApiResponse.ApiResponseSuccess(intersectionService.intersectionSimpleInformation());
    }
}
