package tom.study;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import tom.study.common.config.security.SecurityConfig;

@SpringBootApplication()
@PropertySource("classpath:/application.properties")
public class StudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
