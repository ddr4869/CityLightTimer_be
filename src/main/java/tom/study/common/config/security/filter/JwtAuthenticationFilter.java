package tom.study.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tom.study.common.config.security.jwt.JwtUtil;
import tom.study.common.response.error.errorCode.CommonErrorCode;
import tom.study.common.response.error.handler.GlobalExceptionHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    //private final GlobalExceptionHandler globalExceptionHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        log.info("*** JwtAuthenticationFilter ***");
        try {
            String token = jwtUtil.resolveToken(request.getHeader("Authorization"));
            if (token != null && jwtUtil.validateToken(token)) {
                Authentication authentication = jwtUtil.getAuthenticationFromToken(token);
            }
        } catch (ExpiredJwtException e) {
            log.info("*** Invalid ExpiredJwtException token ***");
            jwtErrResponse(response, CommonErrorCode.EXPIRED_TOKEN);
            return ;
        }  catch (Exception e) {
            log.info("*** Invalid Exception token ***");
            jwtErrResponse(response, CommonErrorCode.INVALID_TOKEN);
            return ;
        }
        chain.doFilter(request, response);
    }

    private void jwtErrResponse(HttpServletResponse response, CommonErrorCode code) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<Object> errorResponse = GlobalExceptionHandler.handleExceptionInternal(code);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse.getBody()));
    }
}
