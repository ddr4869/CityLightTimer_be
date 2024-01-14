package tom.study.api.controller.customer.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.customer.model.entity.Customer;


@Getter
@Setter
public class CustomerLoginRequest {
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String customerPw;
}
