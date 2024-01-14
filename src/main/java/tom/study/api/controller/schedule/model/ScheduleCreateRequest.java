package tom.study.api.controller.schedule.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tom.study.api.controller.reservation.model.ReservationCreateRequest;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.schedule.model.entity.Schedule;

@Getter @Setter @Slf4j
public class ScheduleCreateRequest {
    @NotNull
    private Long screenId;
    @NotNull
    private Long theaterId;
    @NotNull
    private Long movieId;

    public Schedule ModelToEntity(ScheduleCreateRequest scheduleCreateRequest) {
        Schedule schedule = new Schedule();
        schedule.setMovieId(scheduleCreateRequest.getMovieId());
        schedule.setScreenId(scheduleCreateRequest.getScreenId());
        schedule.setTheaterId(scheduleCreateRequest.getTheaterId());
        return schedule;
    }

}
