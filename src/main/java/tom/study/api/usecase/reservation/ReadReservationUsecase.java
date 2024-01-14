package tom.study.api.usecase.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.domain.reservation.service.ReservationService;

@Service
@RequiredArgsConstructor
public class ReadReservationUsecase {

    private final ReservationService reservationService;
    public void execute(ReservationCreateRequest reservationCreateRequest) {
        reservationService.createReservation(reservationCreateRequest.ModelToEntity(reservationCreateRequest));
    }
}
