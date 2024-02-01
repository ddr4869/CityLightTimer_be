package tom.study.domain.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.user.model.entity.Authority;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@Getter
@Setter
public class USERTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotNull
    private String username;

    @Column()
    @NotNull
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<AUTHORITYTEST>  authorities = new ArrayList<>();
}
