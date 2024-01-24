package tom.study.api.controller.intersection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.domain.intersection.service.IntersectionService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/intersection")
@RequiredArgsConstructor
public class IntersectionController {
    private final IntersectionService intersectionService;
    @GetMapping("/list")
    public List<IntersectionResponse> getIntersectionList() {
        return intersectionService.intersectionInformation();
    }

    @GetMapping("/list/simple")
    public List<IntersectionSimpleResponse> getIntersectionSimpleList() throws IOException {
        return intersectionService.intersectionSimpleInformation();
    }
}
