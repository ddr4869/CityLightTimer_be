package tom.study.api.controller.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class RefreshResponse {
    @NotNull
    public String access_token;
    @NotNull
    public String issuer;
    @NotNull
    public Date issuedAt;
    @NotNull
    public Date expired;
}
