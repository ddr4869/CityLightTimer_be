package tom.study.common.config.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tom.study.logger.LogTrace;
import tom.study.logger.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() { return new ThreadLocalLogTrace(); }

}
