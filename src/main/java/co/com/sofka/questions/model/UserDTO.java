package co.com.sofka.questions.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String id;
    private String username;
    private String password;

}
