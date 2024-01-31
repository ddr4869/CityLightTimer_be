package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotNull;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.model.entity.FavoritesCompositeKey;
import tom.study.domain.user.model.entity.User;

public class SignupRequest {
    @NotNull
    public String username;
    @NotNull
    public String password;

    public User ModelToEntity(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.username);
        user.setPassword(signupRequest.password);
        user.setAlgorithm(User.EncryptionAlgorithm.BCRYPT);
        return user;
    }
}
