package tom.study.api.usecase.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tom.study.api.controller.customer.model.CustomerCreateRequest;
import tom.study.common.response.CommonResponse;
import tom.study.domain.customer.service.CustomerService;


@Service
@RequiredArgsConstructor
@Slf4j
public class WriteCustomerUsecase {

    private final CustomerService customerService;

    public ResponseEntity<Object> execute(CustomerCreateRequest customerCreateRequest) {
        return CommonResponse.ResponseEntitySuccess(customerService.createUser(customerCreateRequest.ModelToEntity(customerCreateRequest)));
    }
}
