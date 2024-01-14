package tom.study.domain.item.entity1;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.item.entity1.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
