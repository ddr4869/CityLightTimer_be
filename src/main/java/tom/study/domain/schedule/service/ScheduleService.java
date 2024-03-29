package tom.study.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.domain.schedule.model.entity.Schedule;
import tom.study.domain.schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public Schedule createSchedule(Schedule schedule) {
        log.info("Create {}'s schedule", schedule.getMovieId());
        return scheduleRepository.save(schedule);
    }
}
