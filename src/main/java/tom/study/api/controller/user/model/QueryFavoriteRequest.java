package tom.study.api.controller.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class QueryFavoriteRequest {
    public String userName;
    public int pageNo;
    public int pageSize;
    // constructor, get 3 fields
    public QueryFavoriteRequest(String userName, int pageNo, int pageSize) {
        this.userName = userName;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
