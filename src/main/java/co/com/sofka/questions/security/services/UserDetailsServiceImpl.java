package co.com.sofka.questions.security.services;


import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.collections.UserMajor;
import co.com.sofka.questions.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired(required=true)
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el usuario: " + userName));
        return UserMajor.build(user);
    }



}
