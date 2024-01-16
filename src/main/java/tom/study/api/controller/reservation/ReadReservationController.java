package tom.study.api.controller.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.reservation.model.ReservationQueryAllRequest;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.api.usecase.reservation.ReadReservationUsecase;
import tom.study.domain.reservation.model.entity.Reservation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReadReservationController {
    private final ReadReservationUsecase readReservationUsecase;

    @GetMapping("/view/lists")
    public List<Reservation> getAllReservations() {
        ReservationQueryAllRequest reservationQueryAllRequest = new ReservationQueryAllRequest();
        return readReservationUsecase.execute(reservationQueryAllRequest);
    }

    @GetMapping("/view/{id}")
    public Reservation getReservation(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ReservationQueryRequest reservationQueryRequest = new ReservationQueryRequest();
        reservationQueryRequest.setId(id);
        return readReservationUsecase.execute(reservationQueryRequest);
    }
}
