package tom.study.domain.intersection.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.IntersectionFeignClient;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.IntersectionResponse;
import tom.study.common.feign.resp.IntersectionSimpleResponse;
import tom.study.common.feign.resp.LightFeignResponse;
import tom.study.common.response.ApiResponse;

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

    public ResponseEntity<Object> intersectionInformation() {
        return ApiResponse.ResponseEntitySuccess(intersectionFeignClient.intersectionList(apiKey));
    }

    public ResponseEntity<Object> intersectionSimpleInformation() throws IOException {
        List<IntersectionSimpleResponse> intersections = intersectionFeignClient.intersectionSimpleList(apiKey, "1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("/Users/ieungyu/go/src/github.com/ddr4869/CityLightTimer_be/intersection3.json"), intersections);
        return ApiResponse.ResponseEntitySuccess(intersections);
    }
}