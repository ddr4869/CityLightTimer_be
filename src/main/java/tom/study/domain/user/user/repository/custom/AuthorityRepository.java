package tom.study.domain.user.user.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.user.model.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
