package tom.study.common.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.AuthorityRepository;
import tom.study.domain.user.repository.UserRepository;

import java.util.List;

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
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("user can't found!")
        );
        log.info("findAuthorityByUserName: {}",authorityRepository.findAuthorityByUserName(user.getUsername()));
        List<Authority> authorityList =  authorityRepository.findAuthorityByUserName(user.getUsername());
        log.info("List<Authority>: {}", authorityList);
        user.setAuthorities(authorityRepository.findAuthorityByUserName(user.getUsername()));
        return new EntityUserDetails(user);
    }
}
