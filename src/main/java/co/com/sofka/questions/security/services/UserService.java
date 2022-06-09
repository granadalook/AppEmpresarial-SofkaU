package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired(required = true)
    UserRepository userRepository;

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }


    public boolean existsByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
