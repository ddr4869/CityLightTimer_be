package tom.study.domain.customer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tom.study.api.controller.customer.model.CustomerLoginRequest;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createUser(Customer customer) {
        return customerRepository.save(customer);
    }

//    public void loginUser(CustomerLoginRequest customerLoginRequest) {
//        Authentication authToken = new UsernamePasswordAuthenticationToken(
//                customerLoginRequest.getCustomerName(), customerLoginRequest.getCustomerPw());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
    //customerRepository.save(user);
//  }
}
