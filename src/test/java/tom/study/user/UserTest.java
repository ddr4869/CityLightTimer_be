package tom.study.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import tom.study.common.config.security.UserDetailsServiceImpl;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.repository.AuthorityRepository;
import tom.study.domain.user.repository.UserRepository;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void authorityTest1() {
        List<Authority> authorities = authorityRepository.findDistinctAuthorityByUserName("richard");
        for(Authority authority : authorities) {
            log.info("authority!! : {}", authority.getAuthority());
        }
    }

    @Test
    public void authorityTest() {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository, authorityRepository);
        UserDetails userDetails = userDetailsService.loadUserByUsername("richard");
        log.info("user authority : {}", userDetails.getAuthorities());
    }
}
