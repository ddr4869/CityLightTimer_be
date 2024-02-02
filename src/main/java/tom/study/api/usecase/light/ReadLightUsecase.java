package tom.study.api.usecase.light;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.light.service.LightService;

@RequiredArgsConstructor
@Service
public class ReadLightUsecase {
    private final LightService lightService;
    public ResponseEntity<Object> execute(LightRequest lightRequest) {
        return CommonResponse.ResponseEntitySuccess(lightService.LightTimingMetaInformation(lightRequest));
    }
}
