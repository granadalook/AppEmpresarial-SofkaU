package co.com.sofka.questions.model;

import co.com.sofka.questions.collections.UserInto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUser {
    public Function<UserDTO, UserInto> mapperToUserInto(String id){
        return  updateUserInto ->{
            var userInto = new UserInto();
            userInto.setId(id);
            userInto.setUsername(updateUserInto.getUsername());
            userInto.setPassword(updateUserInto.getPassword());
            return userInto;
        };
    }
}
