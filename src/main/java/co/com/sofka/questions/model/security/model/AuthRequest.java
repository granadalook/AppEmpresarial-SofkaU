package co.com.sofka.questions.model.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequest {
    private String username;
    private String password;
    private String iduser;

}