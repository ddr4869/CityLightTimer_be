package tom.study.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.reservation.repository.custom.CustomReservationRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, CustomReservationRepository {
    List<Reservation> findByCustomerId(String customerId);
    Optional<Reservation> findByReservationPaymentFilm(String reservationPaymentFilm);

    List<Reservation> findFirst2ByCustomerId(String customerId);
}
