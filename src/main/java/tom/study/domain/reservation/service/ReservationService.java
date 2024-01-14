package tom.study.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.reservation.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> queryAllReservation() {
        return reservationRepository.findAll();
    }

}
