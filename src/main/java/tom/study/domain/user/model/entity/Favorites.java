package tom.study.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table
public class Favorites {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @EmbeddedId
    private FavoritesCompositeKey userItsId;
}