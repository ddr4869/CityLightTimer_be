package tom.study.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.model.entity.Authority;

public interface USERTestRepository extends JpaRepository<USERTest, Long> {
}
