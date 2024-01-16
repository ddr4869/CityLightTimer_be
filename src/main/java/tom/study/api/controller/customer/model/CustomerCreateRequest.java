package tom.study.api.controller.customer.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.customer.model.entity.Customer;

@Getter
@Setter
public class CustomerCreateRequest {
    @NotEmpty(message = "customerName을 입력해 주세요.")
    private String customerName;
    @NotEmpty
    private String customerPw;
    @NotEmpty
    private String customerEmail;
    @NotEmpty
    private String  customerTel;
    @NotEmpty
    private String  customerPostcode;
    @NotEmpty
    private String  customerAddress;
    @NotEmpty
    private String  customerDetailAddress;
    @NotEmpty
    private String  customerGrade;
    @NotEmpty
    private String  customerExtraAddress;
    @NotEmpty
    private String  customerDelete;

    public Customer ModelToEntity(CustomerCreateRequest customerCreateRequest) {
        Customer customerEntity = new Customer();
        customerEntity.setCustomerName(customerCreateRequest.getCustomerName());
        customerEntity.setCustomerPw(customerCreateRequest.getCustomerPw());
        customerEntity.setCustomerEmail(customerCreateRequest.getCustomerEmail());
        customerEntity.setCustomerTel(customerCreateRequest.getCustomerTel());
        customerEntity.setCustomerPostcode(customerCreateRequest.getCustomerPostcode());
        customerEntity.setCustomerAddress(customerCreateRequest.getCustomerAddress());
        customerEntity.setCustomerDetailAddress(customerCreateRequest.getCustomerDetailAddress());
        customerEntity.setCustomerGrade(customerCreateRequest.getCustomerGrade());
        customerEntity.setCustomerExtraAddress(customerCreateRequest.getCustomerExtraAddress());
        customerEntity.setCustomerDelete(customerCreateRequest.getCustomerDelete());
        return customerEntity;
    }
}
