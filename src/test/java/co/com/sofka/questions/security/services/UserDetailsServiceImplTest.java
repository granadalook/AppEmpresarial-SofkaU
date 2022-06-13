package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.collections.UserMajor;
import co.com.sofka.questions.security.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.BaseStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserDetailsServiceImplTest extends UserDetailsServiceImpl {

    @MockBean
    private UserRepository userRepository;

    @Test
    public UserDetails loadUserByUsername() throws UsernameNotFoundException{
        var user1 = new User();
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user1.setIdUser("001");
        user1.setUsername("titagena@gmail.com");
        user1.setPassword("123456");
        user1.setRoles(roles);
        UserMajor userMajor = UserMajor.build(user1);
        //UserMajor userMajor1 = new UserMajor(userMajor.getUsername(), userMajor.getPassword(), userMajor.getAuthorities());

        Mockito.when(userRepository.findByUserName("titagena@gmail.com")).thenReturn(instanceof UserMajor ? ((UserMajor) Mockito.when(userRepository.findByUserName("titagena@gmail.com"))) : null));
    }

}