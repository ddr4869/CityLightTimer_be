package tom.study.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;

import java.util.List;

@FeignClient(name = "CrossroadMapInformation", url = "https://t-data.seoul.go.kr/apig/apiman-gateway/tapi/v2xCrossroadMapInformation/1.0")
public interface IntersectionFeignClient {
    @RequestMapping(method = RequestMethod.GET)
    public List<IntersectionResponse> intersectionList(@RequestParam("apiKey") String apiKey);

    @RequestMapping(method = RequestMethod.GET)
    public List<IntersectionSimpleResponse> intersectionSimpleList(@RequestParam("apiKey") String apiKey, @RequestParam("pageNo") String pageNo);
}

