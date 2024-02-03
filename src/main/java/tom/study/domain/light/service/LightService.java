package tom.study.domain.light.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.LightFeignClient;
import tom.study.common.feign.resp.LightFeignMetaResponse;
import tom.study.common.feign.resp.LightFeignResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LightService {
    @Value("${map.naver.apikey}")
    public String apiKey;

    private final LightFeignClient lightFeignClient;

    public List<LightFeignResponse> LightTimingInformation(LightRequest lightRequest) throws FeignException {
        try {
            return lightFeignClient.LightTimingInformation(apiKey, lightRequest.getItstId(), lightRequest.getPageNo(), lightRequest.getPageSize());
        } catch (FeignException e) {
            log.info("!!! FeignException !!!");
            throw e;
        }
    }

    public List<LightFeignMetaResponse> LightTimingMetaInformation(LightRequest lightRequest) throws FeignException {
        try {
            return lightFeignClient.LightTimingMetaInformation(apiKey, lightRequest.getItstId(), lightRequest.getPageNo(), lightRequest.getPageSize());
        } catch (FeignException e) {
            log.info("!!! FeignException !!!");
            throw e;
        }
    }
}
