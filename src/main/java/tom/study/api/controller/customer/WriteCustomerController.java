package tom.study.api.controller.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.controller.customer.model.CustomerCreateRequest;
import tom.study.api.usecase.customer.WriteCustomerUsecase;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WriteCustomerController {
    private final WriteCustomerUsecase writeCustomerUsercase;

    @PostMapping("/create")
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerCreateRequest customerCreateRequest) {
        return writeCustomerUsercase.execute(customerCreateRequest);
    }
}
