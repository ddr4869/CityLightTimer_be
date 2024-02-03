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
        if (!lightRequest.getItstId().matches("^[0-9]*$")) {
            return CommonResponse.ResponseEntityBadRequest("교차로ID(itsId)가 잘못되었습니다.");
        }
        return CommonResponse.ResponseEntitySuccess(lightService.LightTimingMetaInformation(lightRequest));
    }
}
