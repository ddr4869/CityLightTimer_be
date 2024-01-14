package tom.study.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.schedule.model.entity.Schedule;
import tom.study.domain.schedule.repository.custom.CustomScheduleRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, CustomScheduleRepository {
}
