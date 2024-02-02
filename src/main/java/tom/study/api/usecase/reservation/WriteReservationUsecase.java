package tom.study.api.usecase.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.reservation.service.ReservationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteReservationUsecase {

    private final ReservationService reservationService;
    public ResponseEntity<Object> execute(ReservationCreateRequest reservationCreateRequest) {
        return CommonResponse.ResponseEntitySuccess(
                reservationService.createReservation(reservationCreateRequest.ModelToEntity(reservationCreateRequest))
        );
    }
}
