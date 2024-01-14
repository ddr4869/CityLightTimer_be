package tom.study.domain.item.entity2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Parent {
    @Id @GeneratedValue
    @Column(name="PARENT_ID")
    private Long id;
    private String name;

    @OneToOne
    @JoinTable(name="PARENT_CHILD",
            joinColumns = @JoinColumn(name="PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID") )
    private Child child;


}
