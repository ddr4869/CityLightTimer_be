package tom.study.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import tom.study.common.config.security.filter.RequestValidationFilter;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.config.security.login.CustomAuthenticationEntryPoint;
import tom.study.common.config.security.login.LoginFailureHandler;
import tom.study.common.config.security.login.LoginSuccessHandler;
import tom.study.common.response.error.handler.GlobalExceptionHandler;

import java.io.PrintWriter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final RequestValidationFilter requestValidationFilter;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtUtil jwtUtil;
    private final GlobalExceptionHandler globalExceptionHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                // 1,2. 로그인 페이지 설정 -> 사용자가 로그인 페이지에 접근, 로그인 정보를 서버에 제출
                .formLogin(form -> form
                        .loginPage("/login")
                        // 8 로그인 성공 - AuthenticationSuccessHandler 호출
                        .successHandler(loginSuccessHandler)
                        // 9 로그인 실패 - AuthenticationFailureHandler 호출
                        .failureHandler(loginFailureHandler)
                        .permitAll())
                //.formLogin(withDefaults())
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.addAllowedOriginPattern("*"); // 모든 ip에 응답을 허용합니다.
                            corsConfiguration.addAllowedMethod("*");
                            corsConfiguration.addAllowedHeader("*");
                            corsConfiguration.setAllowCredentials(true);
                            corsConfiguration.setMaxAge(3600L);
                            return corsConfiguration;
                        }))
                // 3. UsernamePasswordAuthenticationFilter 전 jwt 인증 filter(JwtAuthenticationFilter)
                // 4. UsernamePasswordAuthenticationFilter는 로그인 정보를 가로채 AuthenticationManager에게 인증을 요청한다.
                //.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/hello") //.permitAll()
                                .hasAnyAuthority("ROLE_ADMIN")
                                .anyRequest().permitAll()

                        //.anyRequest().authenticated()
                )
                .exceptionHandling( exceptionConfig -> exceptionConfig.authenticationEntryPoint(customAuthenticationEntryPoint));
//                .exceptionHandling((exceptionConfig) ->
//                        exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler));
//        http    .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(withDefaults())
//                .addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class)
//                .formLogin(withDefaults())
//                .authorizeHttpRequests(
//                        (authorizeRequests) -> authorizeRequests
//                                .requestMatchers("/", "/login**", "/error", "/api/customer/**").permitAll()
//                                .requestMatchers("/api/user").hasRole("test") )
//                .exceptionHandling((exceptionConfig) ->
//                        exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler)
//              ); // 401 403 관련 예외처리
        log.info("http build");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {
        private final HttpStatus status;
        private final String message;
    }
}
