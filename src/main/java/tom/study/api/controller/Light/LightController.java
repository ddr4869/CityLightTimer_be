package tom.study.api.controller.Light;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LightController {
    private final  LightService lightService;
    @GetMapping("/light")
    public List<LightResponse> getLightTimingFeign(@RequestParam("apiKey") String apiKey, @RequestParam("itstId") String itstId, @RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows) {
        List<LightResponse> response = lightService.call(apiKey, itstId, pageNo, numOfRows);
        log.info("LightController resp: {}", response);
        return response;
    }
}
