package tom.study.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import tom.study.common.response.error.response.ErrorResponse;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final int status;
    private final String code;
    private final String message;
    private final T data;

    // Errors가 없다면 응답이 내려가지 않게 처리
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    public static ApiResponse<Object> ApiResponseSuccess(Object data) {
        return ApiResponse.builder().status(200).code("SUCCESS").message("OK").data(data).build();
    }

    public static ApiResponse<Object> ApiResponseUnauthorized(String message) {
        return ApiResponse.builder().status(401).code("Unauthorized").message(message).build();
    }

    public static ResponseEntity<Object> ResponseEntitySuccess(Object data) {
        return ResponseEntity.status(200).body(ApiResponseSuccess(data));
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        // @Valid 로 에러가 들어왔을 때, 어느 필드에서 에러가 발생했는 지에 대한 응답 처리
        private final String field;
        private final String message;
        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }


}
