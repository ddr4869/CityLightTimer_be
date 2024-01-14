package tom.study.api.controller.reservation.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.reservation.model.entity.Reservation;

@Getter @Setter
public class ReservationCreateRequest {
    @NotEmpty
    private String customerId;

    @NotNull
    private Long reservationPaymentScheduleId;

    @NotEmpty
    private String reservationPaymentFilm;

    public Reservation ModelToEntity(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationCreateRequest.customerId);
        reservation.setReservationPaymentScheduleId(reservationCreateRequest.reservationPaymentScheduleId);
        reservation.setReservationPaymentFilm(reservationCreateRequest.reservationPaymentFilm);
        return reservation;
    }

}
