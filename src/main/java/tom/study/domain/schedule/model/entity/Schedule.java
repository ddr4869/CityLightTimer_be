package tom.study.domain.schedule.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table
@Getter @Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column()
    @NotNull
    private Long screenId;

    @Column()
    @NotNull
    private Long theaterId;

    @Column()
    @NotNull
    private Long movieId;
}
