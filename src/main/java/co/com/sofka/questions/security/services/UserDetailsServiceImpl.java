package co.com.sofka.questions.security.services;


import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.collections.UserMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User User = userService.getByUserName(userName).get();
        return UserMajor.build(User);
    }

}
