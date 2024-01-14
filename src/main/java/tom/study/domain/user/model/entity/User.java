package tom.study.domain.user.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@Getter @Setter
public class User {

//    @Autowired
//    AuthorityRepository authorityRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotNull
    private String username;

    @Column()
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthorities(String auth) {
        if (authorities==null) {
            authorities = new ArrayList<>();
        }
        Authority authority = new Authority();
        authority.setName(auth);
        authority.setUser(this);
        authorities.add(authority);
    }
    public enum EncryptionAlgorithm {
        BCRYPT, SCRYPT
    }
}
