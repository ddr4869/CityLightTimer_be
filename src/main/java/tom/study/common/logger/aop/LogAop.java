package tom.study.common.logger.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import tom.study.common.logger.LogTrace;
import tom.study.common.logger.model.TraceStatus;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAop {

    private final LogTrace logTrace;

    @Pointcut("execution(* tom.study.api..*(..))")
    public void PointcutApi(){}

    @Pointcut("execution(* tom.study.domain..*(..))")
    public void PointcutDomain(){}
    @Pointcut("execution(* tom.study.common.security.jwt..*(..))")
    public void PointcutJwtUtil(){}

    @Around("PointcutApi() || PointcutDomain() || PointcutJwtUtil()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

//        log.info("[log] {}", joinPoint.getSignature());
//        return joinPoint.proceed();
    }
}
