package tom.study.domain.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.user.model.entity.User;

@Data
@Entity
@Table
@Getter
@Setter
public class AUTHORITYTEST {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@ColumnDefault("ROLE_USER")
    @Column
    private String authority;
    @Column
    private String userName;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private USERTest user;
}
