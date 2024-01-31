package tom.study.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter @Setter
public class Authority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@ColumnDefault("ROLE_USER")
    @Column
    private String authority;
    @Column
    private String userName;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    public static class Authority_Type {
        public static String ROLE_USER="ROLE_USER";
        public static String ROLE_ADMIN="ROLE_ADMIN";
    }
}
