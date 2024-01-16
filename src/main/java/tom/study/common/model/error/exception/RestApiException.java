package tom.study.common.model.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tom.study.common.model.error.errorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{

    private final ErrorCode errorCode;

}