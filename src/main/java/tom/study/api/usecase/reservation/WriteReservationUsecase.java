package tom.study.api.usecase.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.domain.reservation.service.ReservationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteReservationUsecase {

    private final ReservationService reservationService;
    public void execute(ReservationCreateRequest reservationCreateRequest) {
        reservationService.createReservation(reservationCreateRequest.ModelToEntity(reservationCreateRequest));
    }
}
