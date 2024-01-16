package tom.study.api.controller.reservation.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationQueryRequest {
    @NotEmpty
    private Long Id;
}
