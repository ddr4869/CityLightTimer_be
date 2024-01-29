package tom.study.domain.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@Embeddable
public class FavoritesCompositeKey implements Serializable {
    private String userName;
    private String itstId;
}
