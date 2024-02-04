package tom.study.api.usecase.intersection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.intersection.model.NeighborIntersectionRequest;
import tom.study.api.controller.light.model.LightRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.intersection.service.IntersectionService;
import tom.study.domain.light.service.LightService;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReadIntersectionUsecase {
    private final IntersectionService intersectionService;
    public ResponseEntity<Object> execute(NeighborIntersectionRequest neighborIntersectionRequest) {
        boolean valid = neighborIntersectionRequest.isLongitudeValid() && neighborIntersectionRequest.isLatitudeValid() && neighborIntersectionRequest.isDistanceValid();
        if (!valid) {
            return CommonResponse.ResponseEntityBadRequest("37.45 < longitude < 37.67, 126.80 < latitude < 127.15, 0 <= distance <= 10000 이어야 합니다.");
        }
        return CommonResponse.ResponseEntitySuccess(intersectionService.neighborIntersectionSimpleInformation(neighborIntersectionRequest));
    }
}
