package tom.study.common.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.repository.CustomerRepository;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.UserRepository;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    //private final List<UserDetails> userDetails;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("name: {}", username);
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("user can't found!")
        );
        return new EntityUserDetails(user);
    }
}
