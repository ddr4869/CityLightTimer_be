package tom.study.api.usecase.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tom.study.api.controller.customer.model.CustomerCreateRequest;
import tom.study.domain.customer.service.CustomerService;


@Service
@RequiredArgsConstructor
@Slf4j
public class WriteCustomerUsecase {

    private final CustomerService customerService;

    public void execute(CustomerCreateRequest customerCreateRequest) {
        customerService.createUser(customerCreateRequest.ModelToEntity(customerCreateRequest));
    }
}
