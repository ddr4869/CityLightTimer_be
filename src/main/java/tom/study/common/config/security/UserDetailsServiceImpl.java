package tom.study.common.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.AuthorityRepository;
import tom.study.domain.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    //private final List<UserDetails> userDetails;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("name: {}", username);

        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("user can't found!")
        );
        user.setAuthorities(authorityRepository.findDistinctAuthorityByUserName(user.getUsername()));
        return new EntityUserDetails(user);
    }
}
