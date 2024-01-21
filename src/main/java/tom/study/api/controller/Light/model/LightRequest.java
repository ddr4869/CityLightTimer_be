package tom.study.api.controller.Light.model;

import lombok.Data;
import lombok.Getter;

//@Getter
@Data
public class LightRequest {
    private String apiKey;
    private String itstId;
    private String pageNo;
    private String numOfRows;
}
