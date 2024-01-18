package tom.study.common.logger;

import lombok.extern.slf4j.Slf4j;
import tom.study.common.logger.model.TraceStatus;

@Slf4j
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace logTrace) {
        this.trace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        }
         catch (Exception e) {
            trace.exception(status, e);
            throw e;
         }
    }

    protected abstract T call();
}
