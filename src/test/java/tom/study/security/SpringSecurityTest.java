package tom.study.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import tom.study.common.config.security.UserDetailsServiceImpl;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.UserRepository;
import tom.study.domain.user.repository.custom.AuthorityRepository;
import tom.study.domain.user.service.UserService;

import java.util.*;

import static tom.study.domain.user.model.entity.User.EncryptionAlgorithm.BCRYPT;

@SpringBootTest
@Slf4j
public class SpringSecurityTest {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Test
    public void CreateUser1() {
        User user = new User();
        user.setUsername("richard");
        user.setPassword("1234");
        user.setAlgorithm(BCRYPT);
        userRepository.save(user);

        Authority authority1 = new Authority();
        authority1.setName("ROLE_MANAGER");
        authority1.setUser(user);
        authorityRepository.save(authority1);

//        Authority authority2 = new Authority();
//        authority2.setName("WRITE");
//        authority2.setUser(user);
//        authorityRepository.save(authority2);
//        log.info("user's auth 3: {}", user.getAuthorities());
    }

    @Test
    public void GetUser1() {
        UserDetails userDetails = userService.loadUserByUsername("test6");
        Collection<? extends GrantedAuthority> auths = userDetails.getAuthorities();
    }

    @Test
    public void GetUser2() {
        UserDetails userA = org.springframework.security.core.userdetails.User
                .withUsername("jinseok")
                .password("1234")
                .authorities("WRITE", "READ", "UPDATE")
                .roles("qwe")
                .build();
    }

    @Test
    public void securityTest1() {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("richard");
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("SUPER"))) {
            log.info("현재 사용자 {}는 'SUPER' 권한이 있습니다.", userDetails.getUsername());
        } else {
            log.info("현재 사용자 {}는 'SUPER' 권한이 없습니다.", userDetails.getUsername());
        }
    }

    @Test
    public void securityTest2() {
        Authentication authToken = new UsernamePasswordAuthenticationToken("tom2", "1234");
        SecurityContextHolder.getContext().setAuthentication(authToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("authorities: {}", authorities.toString());
        if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("SUPER"))) {
            log.info("현재 사용자 {}는 'SUPER' 권한이 있습니다.", authentication.getName());
        } else {
            log.info("현재 사용자 {}는 'SUPER' 권한이 없습니다.", authentication.getName());
        }
    }
}
