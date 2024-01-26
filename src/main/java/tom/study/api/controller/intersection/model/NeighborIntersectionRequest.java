package tom.study.api.controller.intersection.model;

import lombok.Data;

@Data
public class NeighborIntersectionRequest {
    private String latitude; // 위도 37.xx
    private String longitude; // 경도 127/xx
    private String distance;
}
