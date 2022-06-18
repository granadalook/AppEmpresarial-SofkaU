package co.com.sofka.questions.model;

import co.com.sofka.questions.collections.UserInto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUser {
    public Function<UserDTO, UserInto> mapperToUserInto(){
        return  updateUserInto ->{
            var userInto = new UserInto();
            userInto.setUsername((updateUserInto.getUsername()));
            userInto.setPassword(updateUserInto.getPassword());
            return userInto;
        };
    }
    public  Function<UserInto,UserDTO> mapperToUserDto(){
        return updateUserDto ->{
            var userDto = new  UserDTO();
            userDto.setId(userDto.getId());
            userDto.setPassword(updateUserDto.getPassword());
            userDto.setUsername(updateUserDto.getUsername());
            return  userDto;
        };
    }
}
