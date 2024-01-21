package tom.study.api.usecase.light;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tom.study.api.controller.Light.model.LightRequest;
import tom.study.common.feign.resp.LightFeignResponse;
import tom.study.domain.light.service.LightService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReadLightUsecase {
    private final LightService lightService;
    public List<LightFeignResponse> execute(LightRequest lightRequest) {
        return lightService.LightTimingInformation(lightRequest);
    }
}
