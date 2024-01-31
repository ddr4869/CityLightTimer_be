package tom.study.common.config.security.filter.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import tom.study.common.response.error.errorCode.CommonErrorCode;
import tom.study.common.response.error.handler.GlobalExceptionHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomJwtEntryPoint implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("!!! CustomJwtEntryPoint !!!");
        jwtErrResponse(response, CommonErrorCode.INVALID_TOKEN);
    }
    private void jwtErrResponse(HttpServletResponse response, CommonErrorCode code) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<Object> errorResponse = GlobalExceptionHandler.handleExceptionInternal(code);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse.getBody()));
    }
}
