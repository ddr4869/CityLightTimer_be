package tom.study.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.model.entity.Authority;

import java.util.List;

public interface AUTHORITYTestRepository extends JpaRepository<AUTHORITYTEST, Long> {
    List<AUTHORITYTEST> findAuthorityByUserName(String userName);
}
