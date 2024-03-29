package tom.study.domain.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.model.entity.Favorites;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    //List<Favorites> findByUserName(String userName);
    Page<Favorites> findByUserItsIdUserName(String userName, Pageable pageable);
}
