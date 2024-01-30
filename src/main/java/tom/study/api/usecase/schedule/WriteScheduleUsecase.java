package tom.study.api.usecase.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.common.response.ApiResponse;
import tom.study.domain.schedule.model.entity.Schedule;
import tom.study.domain.schedule.service.ScheduleService;

@Service
@RequiredArgsConstructor
public class WriteScheduleUsecase {
    private final ScheduleService scheduleService;

    public ResponseEntity<Object> execute(ScheduleCreateRequest scheduleCreateRequest) {
        return ApiResponse.ResponseEntitySuccess(scheduleService.createSchedule(scheduleCreateRequest.ModelToEntity(scheduleCreateRequest)));
    }
}
