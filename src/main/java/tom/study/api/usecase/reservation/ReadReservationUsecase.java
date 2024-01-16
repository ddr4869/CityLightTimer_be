package tom.study.api.usecase.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.api.controller.reservation.model.ReservationQueryAllRequest;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.reservation.service.ReservationService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadReservationUsecase {

    private final ReservationService reservationService;
    public List<Reservation> execute(ReservationQueryAllRequest reservationQueryAllRequest) {
        return reservationService.queryAllReservations();
    }

    public Reservation execute(ReservationQueryRequest reservationQueryRequest) {
        return reservationService.queryReservation(reservationQueryRequest);
    }
}
