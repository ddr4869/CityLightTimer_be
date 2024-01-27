package tom.study.common.response.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tom.study.common.response.error.errorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ErrorCode errorCode;
}