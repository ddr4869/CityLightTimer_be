package tom.study.api.usecase.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tom.study.api.controller.user.model.CreateFavoriteRequest;
import tom.study.api.controller.user.model.DeleteFavoriteRequest;
import tom.study.api.controller.user.model.EmpowermentRequest;
import tom.study.api.controller.user.model.SignupRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.user.service.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class WriteUserUsecase {
    private final UserService userService;

    public ResponseEntity<Object> execute(CreateFavoriteRequest createFavoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        createFavoriteRequest.userName=authentication.getName();
        return CommonResponse.ResponseEntitySuccess(userService.addFavorites(createFavoriteRequest.ModelToEntity(createFavoriteRequest)));
    }

    public ResponseEntity<Object> execute(DeleteFavoriteRequest deleteFavoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        deleteFavoriteRequest.userName=authentication.getName();
        userService.delFavorites(deleteFavoriteRequest.ModelToEntity(deleteFavoriteRequest));
        return CommonResponse.ResponseEntitySuccessMessage();
    }

    public ResponseEntity<Object> execute(SignupRequest signupRequest) {
        return CommonResponse.ResponseEntitySuccess(userService.signupUser(signupRequest.ModelToEntity(signupRequest)));
    }

    public ResponseEntity<Object> execute(EmpowermentRequest empowermentRequest) {
        userService.empowermentUser(empowermentRequest.getUsername());
        return CommonResponse.ResponseEntitySuccessMessage();
    }
}
