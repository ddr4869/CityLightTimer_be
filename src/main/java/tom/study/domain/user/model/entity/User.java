package tom.study.domain.user.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "유저 ID")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Schema(description = "유저 이름")
    private String username;

    @Column()
    @NotNull
    @Schema(description = "유저 비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "암호화 알고리즘")
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "권한")
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthorities(String auth) {
        if (authorities==null) {
            authorities = new ArrayList<>();
        }
        Authority authority = new Authority();
        authority.setAuthority(auth);
        authority.setUser(this);
        authorities.add(authority);
    }
    public enum EncryptionAlgorithm {
        BCRYPT, SCRYPT
    }
}
