package tom.study.api.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.schedule.model.ScheduleCreateRequest;
import tom.study.api.controller.user.model.LoginUserRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WriteUserController {
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginUserRequest loginUserRequest) {
        log.info("login test");
        return "login test";
    }
}
