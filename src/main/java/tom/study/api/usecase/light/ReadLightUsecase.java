package tom.study.api.usecase.light;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.feign.resp.LightFeignMetaResponse;
import tom.study.common.feign.resp.LightFeignResponse;
import tom.study.common.response.ApiResponse;
import tom.study.domain.light.service.LightService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReadLightUsecase {
    private final LightService lightService;
    public ResponseEntity<Object> execute(LightRequest lightRequest) {
        return ApiResponse.ResponseEntitySuccess(lightService.LightTimingMetaInformation(lightRequest));
    }
}
