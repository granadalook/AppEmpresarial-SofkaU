package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

   public void save(User user){
    userRepository.save(user);
    }

    public  User getUserByUserName(String userName){
        User user= userRepository.findUserByUserName(userName);
        return  user;
    }
}
