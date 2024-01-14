package tom.study.domain.customer.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tom.study.domain.reservation.model.entity.Reservation;

import java.util.List;

@Data
@Entity
@Table
@Getter @Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotEmpty
    private String customerName;

    @Column()
    @NotEmpty
    private String customerPw;

    @Column()
    @NotEmpty
    private String customerEmail;

    @Column()
    @NotEmpty
    private String customerTel;

    @Column()
    @NotEmpty
    private String customerPostcode;

    @Column()
    @NotEmpty
    private String customerAddress;

    @Column()
    @NotEmpty
    private String customerDetailAddress;

    @Column()
    @NotEmpty
    private String customerGrade;

    @Column()
    @NotEmpty
    private String customerExtraAddress;

    @Column()
    @NotEmpty
    private String customerDelete;

//    @OneToMany(mappedBy = "user")
//    private List<Reservation> reservations;
}
