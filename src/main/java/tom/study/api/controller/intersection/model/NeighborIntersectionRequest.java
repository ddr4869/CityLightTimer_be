package tom.study.api.controller.intersection.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class NeighborIntersectionRequest {
    @NotNull
    @Schema(description = "위도, ex) 37.xxxxxx")
    private Double latitude; // 위도 37.xx
    @NotNull
    @Schema(description = "경도, ex) 127.xxxxxx")
    private Double longitude; // 경도 127.xx
    @NotNull
    @Schema(description = "거리, 단위-m, ex) 1000")
    private int distance;

    public NeighborIntersectionRequest(Double latitude, Double longitude, int distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public boolean isLatitudeValid() {
        return 37.45 < this.latitude && this.latitude< 37.67;
    }

    public boolean isLongitudeValid() {
        return 126.80 < this.longitude && this.longitude < 127.15;
    }

    public boolean isDistanceValid() {
        return 0 <= this.distance && this.distance <= 10000;
    }
}
