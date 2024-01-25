package tom.study;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import tom.study.common.config.security.SecurityConfig;
import tom.study.common.logger.LogTrace;
import tom.study.common.logger.ThreadLocalLogTrace;
import tom.study.common.logger.aop.LogAop;

@SpringBootApplication()
@EnableFeignClients
@PropertySource("classpath:/application.yaml")
public class StudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

	@Bean
	public LogAop logAop(LogTrace logTrace) {
		return new LogAop(logTrace);
	}
}
