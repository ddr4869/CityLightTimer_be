package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.UserRepository;

import java.util.Optional;

@Data
@Slf4j
public class EmpowermentRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
