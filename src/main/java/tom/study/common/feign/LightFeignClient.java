package tom.study.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tom.study.common.feign.resp.LightFeignResponse;

import java.util.List;

@FeignClient(name = "SignalPhaseTimingInformation", url = "https://t-data.seoul.go.kr/apig/apiman-gateway/tapi/v2xSignalPhaseTimingInformation/1.0")
public interface LightFeignClient {
    @RequestMapping(method = RequestMethod.GET)
    public List<LightFeignResponse> LightTimingInformation(@RequestParam("apiKey") String apiKey, @RequestParam("itstId") String itstId, @RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows);

    @RequestMapping(method = RequestMethod.GET)
    public List<LightFeignResponse> LightTimingSimpleInformation(@RequestParam("apiKey") String apiKey, @RequestParam("itstId") String itstId, @RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows);
}