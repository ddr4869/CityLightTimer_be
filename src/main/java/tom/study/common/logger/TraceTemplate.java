package tom.study.common.logger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import tom.study.common.logger.model.TraceStatus;

@RequiredArgsConstructor
@Configuration
public class TraceTemplate {
    private final LogTrace trace;

    public <T> T execute(String message, TraceCallBack<T> callBack) {
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            //로직 호출
            T result = callBack.call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
