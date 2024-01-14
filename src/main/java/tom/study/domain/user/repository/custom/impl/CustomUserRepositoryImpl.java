package tom.study.domain.user.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tom.study.domain.user.repository.custom.CustomUserRepository;

@RequiredArgsConstructor
@Slf4j
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
