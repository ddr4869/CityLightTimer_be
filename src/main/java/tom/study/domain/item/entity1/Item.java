package tom.study.domain.item.entity1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter @Setter
public class Item {
    @Id @GeneratedValue
    @Column
    private Long id;
    private String name;
    private int price;
}
