package tom.study.api.controller.Light;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test", url = "http://t-data.seoul.go.kr/apig/apiman-gateway/tapi/v2xSignalPhaseTimingInformation/1.0")
public interface LightFeign {
    @GetMapping
    public String call(LightRequest lightRequest);
}
