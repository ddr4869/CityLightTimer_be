package tom.study.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tom.study.api.controller.reservation.model.ReservationQueryRequest;
import tom.study.api.usecase.reservation.ReadReservationUsecase;
import tom.study.common.config.security.UserDetailsServiceImpl;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.UserRepository;
import tom.study.domain.user.repository.custom.AuthorityRepository;
import tom.study.domain.user.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static tom.study.domain.user.model.entity.User.EncryptionAlgorithm.BCRYPT;

@SpringBootTest
@Slf4j
public class SpringSecurityTest {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    ReadReservationUsecase readReservationUsecase;

    @Value("${jwt.secret}")
    private String secretKey;


    @Test
    public void CreateUser1() {
        User user1 = new User();
        user1.setUsername("richard");
        user1.setPassword("1234");
        user1.setAlgorithm(BCRYPT);
        userRepository.save(user1);

        Authority authority1 = new Authority();
        authority1.setName("ROLE_USER");
        authority1.setUser(user1);
        authorityRepository.save(authority1);

        User user2 = new User();
        user2.setUsername("tom");
        user2.setPassword("1234");
        user2.setAlgorithm(BCRYPT);
        userRepository.save(user2);

        Authority authority2 = new Authority();
        authority2.setName("ROLE_ADMIN");
        authority2.setUser(user2);
        authorityRepository.save(authority2);
    }

    @Test
    public void GetUser1() {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("tom");
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

    @Test
    public void getReservationTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("name: {}", authentication.getName());
        ReservationQueryRequest reservationQueryRequest = new ReservationQueryRequest();
        reservationQueryRequest.setId(1L);
        Reservation reservation = readReservationUsecase.execute(reservationQueryRequest);
    }

    @Test
    public void jwtTest1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        JwtUtil jwtUtil = new JwtUtil();
        String jwtStr = jwtUtil.createAccessJwt("tom");
        log.info("token: {}", jwtStr);
    }

}
