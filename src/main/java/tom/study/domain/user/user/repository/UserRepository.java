package tom.study.domain.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.user.model.entity.User;
import tom.study.domain.user.user.repository.custom.CustomUserRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    Optional<User> findUserByUsername(String username);
}
