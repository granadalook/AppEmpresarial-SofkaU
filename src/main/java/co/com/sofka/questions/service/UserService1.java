package co.com.sofka.questions.service;

import co.com.sofka.questions.collections.UserInto;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



//import javax.transaction.Transactional;


@Service
public class UserService1 {


    @Autowired
    UserRepository userRepository;

/*    public Mono<UserInto> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }*/

    public void save(UserInto user){
        userRepository.save(user);
    }

 /*   public  Mono<UserInto> getUserByUsername(String username){
        Mono<UserInto> user= userRepository.findUserByUsername(username);
        System.out.println(user);
              return  user;
  }*/
}
