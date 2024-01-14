package tom.study.domain.user.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.model.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
