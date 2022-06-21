package co.com.sofka.questions.service;

import co.com.sofka.questions.collections.UserInto;
import co.com.sofka.questions.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


//import javax.transaction.Transactional;


@Service
@Validated
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /*public Mono<UserInto> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }*/

    /*public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }*/

    public Mono<UserInto> save(UserInto user) {
        return userRepository.save(user);
    }

   public  Mono<UserInto> getUserByUsername(String username){
       return  userRepository.findUserByUsername(username);

  }
}
