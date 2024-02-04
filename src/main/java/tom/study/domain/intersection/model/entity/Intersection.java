package tom.study.domain.intersection.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Intersection {
    @Id
    private String itstId;
    @Column
    private String itstNm;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

    public Intersection(String itstId, String itstNm, Double latitude, Double longitude) {
        this.itstId = itstId;
        this.itstNm = itstNm;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
