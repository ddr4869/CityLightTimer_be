package tom.study.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tom.study.common.config.security.EntityUserDetails;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
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
