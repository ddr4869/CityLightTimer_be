package tom.study.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.schedule.model.entity.Schedule;
import tom.study.domain.user.model.entity.Authority;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findDistinctAuthorityByUserName(String userName);
}
