package tom.study.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Authority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authority;

    private String userName;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}
