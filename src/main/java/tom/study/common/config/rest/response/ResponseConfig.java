package tom.study.common.config.rest.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@Slf4j
public class ResponseConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        log.info("restTimeout set ... ");

        return restTemplateBuilder.
                setConnectTimeout(Duration.ofSeconds(5)).
                setReadTimeout(Duration.ofSeconds(5)).
                build();
    }
}
