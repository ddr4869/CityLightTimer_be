package tom.study.api.controller.light.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestParam;

//@Getter
@Data
public class LightRequest {
    public LightRequest(String itstId, String pageNo, String numOfRows) {
        this.itstId = itstId;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
    }
    private String itstId;
    private String pageNo;
    private String numOfRows;
}
