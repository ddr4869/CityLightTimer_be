package tom.study.domain.schedule.repository.custom;

import com.querydsl.core.Tuple;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.schedule.model.entity.QSchedule;
import tom.study.domain.schedule.model.entity.Schedule;

import java.util.List;

public interface CustomScheduleRepository {
    List<Schedule> CustomersReservateSchdules(String customName);
}
