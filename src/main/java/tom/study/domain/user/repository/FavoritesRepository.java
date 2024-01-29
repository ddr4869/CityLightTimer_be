package tom.study.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.user.model.entity.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
