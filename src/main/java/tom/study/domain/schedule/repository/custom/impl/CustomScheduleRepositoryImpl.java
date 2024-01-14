package tom.study.domain.schedule.repository.custom.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.model.entity.QCustomer;
import tom.study.domain.reservation.model.entity.QReservation;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.schedule.model.entity.QSchedule;
import tom.study.domain.schedule.model.entity.Schedule;
import tom.study.domain.schedule.repository.custom.CustomScheduleRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Schedule> CustomersReservateSchdules(String customName) {
        QSchedule qSchedule = QSchedule.schedule;
        QCustomer qCustomer = QCustomer.customer;
        QReservation qReservation = QReservation.reservation;

        // join where fetch
        return jpaQueryFactory.selectFrom(qSchedule).
                join(qReservation).on(qReservation.reservationPaymentId.eq(qSchedule.movieId)).
                where(qReservation.customerId.eq(customName)).
                fetch();
    }
}
