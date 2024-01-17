package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class LoginUserRequest {
    @NotNull
    public String username;
    @NotNull
    public String password;
}
