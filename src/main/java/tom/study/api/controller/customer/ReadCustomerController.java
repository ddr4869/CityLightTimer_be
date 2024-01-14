package tom.study.api.controller.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tom.study.api.usecase.customer.ReadCustomerUsecase;
import tom.study.api.controller.customer.model.CustomerLoginRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ReadCustomerController {
    private ReadCustomerUsecase readCustomerUsecase;

//    @PostMapping("/login")
//    public void login(@RequestBody @Valid CustomerLoginRequest customerLoginRequest) {
//
//        //readCustomerUsecase.execute(customerLoginRequest);
//    }


}
