package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotNull;
import tom.study.domain.user.model.entity.User;

public class EmpowermentRequest {
    @NotNull
    public String username;
    @NotNull
    public String password;

    public User ModelToEntity(EmpowermentRequest empowermentRequest) {
        User user = new User();
        user.setUsername(empowermentRequest.username);
        user.setPassword(empowermentRequest.password);
        user.setAlgorithm(User.EncryptionAlgorithm.BCRYPT);
        return user;
    }
}
