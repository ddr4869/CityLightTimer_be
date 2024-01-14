package tom.study.domain.item.entity1;

import org.springframework.data.jpa.repository.JpaRepository;
import tom.study.domain.item.entity1.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
