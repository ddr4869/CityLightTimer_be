package tom.study.domain.intersection.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.IntersectionFeignClient;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.feign.resp.LightFeignResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntersectionService {
    private final IntersectionFeignClient intersectionFeignClient;
    private final LightFeignClient lightFeignClient;
    @Value("${map.naver.apikey}")
    public String apiKey;

    public List<IntersectionResponse> intersectionInformation() {
        return intersectionFeignClient.intersectionList(apiKey);
    }

    public List<IntersectionSimpleResponse> intersectionSimpleInformation() throws IOException {
        List<IntersectionSimpleResponse> intersections = intersectionFeignClient.intersectionSimpleList(apiKey, "1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/mySpringBoard/intersection3.json"), intersections);
        return intersections;
    }

    public void intersectionSimpleInformationIfLightTimingExist() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IntersectionSimpleResponse> intersections = objectMapper.readValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/mySpringBoard/intersection1.json"), new TypeReference<List<IntersectionSimpleResponse>>() {});
        int cnt = 0;
        for (IntersectionSimpleResponse intersectionSimpleResponse: intersections) {
            if (cnt==10) break;
            log.info("feign starting ...");
            List<LightFeignResponse> light = lightFeignClient.LightTimingInformation(apiKey, intersectionSimpleResponse.getItstId(), "1", "1");
            if (!light.isEmpty()) {
                log.info("resp: {}", light.get(0).getItstId());
            }
            cnt+=1;
        }
    }

    public void intersectionSimpleInformationIfLightTimingExist2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //List<IntersectionSimpleResponse> intersections = objectMapper.readValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/mySpringBoard/intersection1.json"), new TypeReference<List<IntersectionSimpleResponse>>() {});
            List<LightFeignResponse> light = lightFeignClient.LightTimingInformation(apiKey, "22904", "1", "1");
                log.info("resp: {}", light.get(0).toString());
    }
}