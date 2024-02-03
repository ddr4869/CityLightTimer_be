package tom.study.api.usecase.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tom.study.api.controller.customer.model.CustomerCreateRequest;
import tom.study.api.controller.user.model.QueryFavoriteRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.customer.service.CustomerService;
import tom.study.domain.user.model.entity.Favorites;
import tom.study.domain.user.service.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReadUserUsecase {

    private final UserService userService;

    public ResponseEntity<Object> execute(QueryFavoriteRequest queryFavoriteRequest) {
        return CommonResponse.ResponseEntitySuccess(userService.queryFavorites(queryFavoriteRequest));
    }

}
