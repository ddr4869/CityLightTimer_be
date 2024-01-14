package tom.study.domain.reservation.repository.custom;

import com.querydsl.core.Tuple;
import tom.study.domain.reservation.model.entity.Reservation;

import java.util.List;

public interface CustomReservationRepository {
    List<Reservation> findReservations();
    List<Tuple> findReservations2();
    List<Reservation> findReservations3(String customName);

    List<Reservation> findReservations4(String customName);
}
