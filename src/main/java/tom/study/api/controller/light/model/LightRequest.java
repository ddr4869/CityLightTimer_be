package tom.study.api.controller.light.model;

import lombok.Data;

//@Getter
@Data
public class LightRequest {
    private String itstId;
    private String pageNo;
    private String numOfRows;
}
