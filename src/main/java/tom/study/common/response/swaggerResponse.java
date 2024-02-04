package tom.study.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import tom.study.domain.user.model.entity.Favorites;

import java.util.List;

/**
 * @param errors Errors가 없다면 응답이 내려가지 않게 처리
 */

public class swaggerResponse {
    @Schema(description = "status code")
    public int status;
    @Schema(description = "Data")
    public Favorites data;
}


