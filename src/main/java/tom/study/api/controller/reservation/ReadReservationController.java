package tom.study.api.controller.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.reservation.model.ReservationQueryAllRequest;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.api.usecase.reservation.ReadReservationUsecase;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReadReservationController {
    private final ReadReservationUsecase readReservationUsecase;
    @GetMapping("/view/lists")
    public ResponseEntity<Object> getAllReservations() {
        ReservationQueryAllRequest reservationQueryAllRequest = new ReservationQueryAllRequest();
        return readReservationUsecase.execute(reservationQueryAllRequest);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> getReservation(@PathVariable("id") Long id) {
        ReservationQueryRequest reservationQueryRequest = new ReservationQueryRequest();
        reservationQueryRequest.setId(id);
        return readReservationUsecase.execute(reservationQueryRequest);
    }
}
