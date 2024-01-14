package tom.study.api.controller.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tom.study.api.usecase.customer.ReadCustomerUsecase;
import tom.study.api.controller.customer.model.CustomerLoginRequest;

@RestController
@RequiredArgsConstructor
public class ReadCustomerController {
    private ReadCustomerUsecase readCustomerUsecase;
}
