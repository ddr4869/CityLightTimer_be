package tom.study.api.controller.schedule;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.api.usecase.schedule.WriteScheduleUsecase;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Slf4j
public class WriteScheduleController {
    private final WriteScheduleUsecase writeScheduleUsecase;

    @PostMapping("/create")
    public void createSchedule(@RequestBody @Valid ScheduleCreateRequest scheduleCreateRequest) {
        writeScheduleUsecase.execute(scheduleCreateRequest);
    }
}
