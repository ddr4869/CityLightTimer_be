package tom.study.api.usecase.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.reservation.model.ReservationQueryAllRequest;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.reservation.service.ReservationService;

@Service
@RequiredArgsConstructor
public class ReadReservationUsecase {

    private final ReservationService reservationService;
    public ResponseEntity<Object> execute(ReservationQueryAllRequest reservationQueryAllRequest) {
        return CommonResponse.ResponseEntitySuccess(reservationService.queryAllReservations());
    }

    public ResponseEntity<Object> execute(ReservationQueryRequest reservationQueryRequest) {
        return CommonResponse.ResponseEntitySuccess(reservationService.queryReservation(reservationQueryRequest));
    }
}
