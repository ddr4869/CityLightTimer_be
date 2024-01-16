package tom.study.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.pattern.PatternParseException;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.reservation.repository.ReservationRepository;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        log.info("Create {}'sReservation", reservation.getCustomerId());
        return reservationRepository.save(reservation);
    }

    public List<Reservation> queryAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation queryReservation(ReservationQueryRequest reservationQueryRequest) {
        return reservationRepository.findById(reservationQueryRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation doesn't exist"));
    }
}

