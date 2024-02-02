package tom.study.api.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.api.usecase.reservation.WriteReservationUsecase;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Slf4j
public class WriteReservationController {
    private final WriteReservationUsecase writeReservationUsecase;

    @PostMapping("/create")
    public ResponseEntity<Object> createReservation(@RequestBody ReservationCreateRequest reservationCreateRequest) {
        return writeReservationUsecase.execute(reservationCreateRequest);
    }
}
