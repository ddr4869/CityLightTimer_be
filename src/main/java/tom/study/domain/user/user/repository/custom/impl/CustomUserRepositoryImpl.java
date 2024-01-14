package tom.study.domain.user.user.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tom.study.domain.customer.model.entity.QCustomer;
import tom.study.domain.reservation.model.entity.QReservation;
import tom.study.domain.schedule.model.entity.QSchedule;
import tom.study.domain.user.user.model.entity.User;
import tom.study.domain.user.user.repository.custom.CustomUserRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
