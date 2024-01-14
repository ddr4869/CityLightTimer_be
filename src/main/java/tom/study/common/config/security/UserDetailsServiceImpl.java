package tom.study.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.repository.CustomerRepository;

import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {
    private final List<UserDetails> userDetails;

    public UserDetailsServiceImpl(List<UserDetails> userDetails) {
        this.userDetails=userDetails;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetails.stream().filter(user -> user.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found Exception"));
    }
}
