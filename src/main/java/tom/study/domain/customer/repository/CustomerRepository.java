package tom.study.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.customer.model.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerName(String customerName);
}
