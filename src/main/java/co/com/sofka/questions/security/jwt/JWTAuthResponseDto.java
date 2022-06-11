package co.com.sofka.questions.security.jwt;

public class JWTAuthResponseDto {

    private String tokenAccess;
    private String tokenType = "Bearer";

    public JWTAuthResponseDto(String tokenAccess) {
        super();
        this.tokenAccess = tokenAccess;
    }

    public JWTAuthResponseDto(String tokenAccess, String tokenType) {
        super();
        this.tokenAccess = tokenAccess;
        this.tokenType = tokenType;
    }

    public String getTokenAccess() {
        return tokenAccess;
    }

    public void setTokenAccess(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


}
