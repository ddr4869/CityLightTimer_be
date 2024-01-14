package tom.study.domain.item.entity1;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.item.entity1.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
