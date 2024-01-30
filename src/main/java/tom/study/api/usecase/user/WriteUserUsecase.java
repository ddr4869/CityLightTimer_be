package tom.study.api.usecase.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tom.study.api.controller.customer.model.CustomerCreateRequest;
import tom.study.api.controller.user.model.CreateFavoriteRequest;
import tom.study.api.controller.user.model.DeleteFavoriteRequest;
import tom.study.common.response.ApiResponse;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.service.CustomerService;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.service.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class WriteUserUsecase {
    private final UserService userService;

    public ResponseEntity<Object> execute(CreateFavoriteRequest createFavoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        createFavoriteRequest.userName=authentication.getName();
        return ApiResponse.ResponseEntitySuccess(userService.addFavorites(createFavoriteRequest.ModelToEntity(createFavoriteRequest)));
    }

    public ResponseEntity<Object> execute(DeleteFavoriteRequest deleteFavoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        deleteFavoriteRequest.userName=authentication.getName();
        userService.delFavorites(deleteFavoriteRequest.ModelToEntity(deleteFavoriteRequest));
        return ApiResponse.ResponseEntitySuccessMessage();
    }
}
