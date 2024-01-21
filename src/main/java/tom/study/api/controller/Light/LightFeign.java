package tom.study.api.controller.Light;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "test", url = "https://t-data.seoul.go.kr/apig/apiman-gateway/tapi/v2xSignalPhaseTimingInformation/1.0")
public interface LightFeign {
    @RequestMapping(method = RequestMethod.GET)
    public List<LightResponse> call(@RequestParam("apiKey") String apiKey, @RequestParam("itstId") String itstId, @RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows);
}
