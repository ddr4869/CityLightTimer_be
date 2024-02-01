package tom.study.jpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tom.study.domain.test.AUTHORITYTEST;
import tom.study.domain.test.AUTHORITYTestRepository;
import tom.study.domain.test.USERTest;
import tom.study.domain.test.USERTestRepository;
import tom.study.domain.user.model.entity.Authority;
import tom.study.domain.user.model.entity.User;
import tom.study.domain.user.repository.AuthorityRepository;
import tom.study.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static tom.study.domain.user.model.entity.User.EncryptionAlgorithm.BCRYPT;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class jpaTest {
    @Autowired
    USERTestRepository userTestRepository;
    @Autowired
    AUTHORITYTestRepository authorityTestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    void UserInsert() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");

        Authority authority = new Authority();
        authority.setUserName("test");
        authority.setAuthority("ROLE_USER");
        List<Authority> a =  new ArrayList<>();
        a.add(authority);
        user.setAuthorities(a);
        userRepository.save(user);
        authorityRepository.save(authority);
    }

    @Test
    void AuthorityUpdateTest() {
        // chage authority to ROLE_ADMIN
        Optional<Authority> authority = authorityRepository.findById(1L);
        authority.ifPresent(a -> {
            a.setAuthority("ROLE_USER");
            authorityRepository.save(a);
        });
    }

    @Test
    void UserDeleteTest() {
        // delete user
        Optional<User> user = userRepository.findByUsername("test1");
        user.ifPresent(u -> {
            userRepository.delete(u);
        });
    }
}
