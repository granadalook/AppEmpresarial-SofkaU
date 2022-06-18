package co.com.sofka.questions.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "UserInto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInto {
    @Id
    private String id;
    private String username;
    private String password;


}
