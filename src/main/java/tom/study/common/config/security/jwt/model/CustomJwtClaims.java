package tom.study.common.config.security.jwt.model;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CustomJwtClaims {
    @NotNull
    public String access_token;
    @NotNull
    public String issuer;
    @NotNull
    public Date issuedAt;
    @NotNull
    public Date expired;
}
