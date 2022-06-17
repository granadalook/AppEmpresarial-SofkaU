package co.com.sofka.questions.service;

import co.com.sofka.questions.model.User;
import co.com.sofka.questions.repositories.UserRepository;
//import co.com.sofka.questions.security.collections.User;
 //import co.com.sofka.questions.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

//import javax.transaction.Transactional;
import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService1 {

    @Autowired
    UserRepository userRepository;

    public Mono<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }


    public  Mono<User> getUserByUserName(String username){
        System.out.println("USER NAME SERVICIO  ***********         "+ username);
        Mono<User> user= userRepository.findUserByUsername(username);
        System.out.println("USER  SERVICIO  RETUR ***********      "+ user);
        return  user;
  }
}
