package tom.study.api.usecase.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.domain.schedule.service.ScheduleService;

@Service
@RequiredArgsConstructor
public class WriteScheduleUsecase {
    private final ScheduleService scheduleService;

    public void execute(ScheduleCreateRequest scheduleCreateRequest) {
        scheduleService.createSchedule(scheduleCreateRequest.ModelToEntity(scheduleCreateRequest));
    }
}
