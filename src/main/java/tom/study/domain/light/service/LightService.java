package tom.study.domain.light.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.api.controller.Light.model.LightRequest;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.LightFeignResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LightService {
    private final LightFeignClient lightFeignClient;

    public List<LightFeignResponse> LightTimingInformation(LightRequest lightRequest) {
        return lightFeignClient.LightTimingInformation(lightRequest.getApiKey(), lightRequest.getItstId(), lightRequest.getPageNo(), lightRequest.getNumOfRows());
    }
}
