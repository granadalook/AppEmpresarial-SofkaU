package co.com.sofka.questions.security.services;

import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void getByUserName() {
        var user1 = new User();
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user1.setIdUser("001");
        user1.setUsername("titagena@gmail.com");
        user1.setPassword("123456");
        user1.setRoles(roles);
        Mockito.when(userRepository.findByUserName("titagena@gmail.com")).thenReturn(Optional.of(user1));

        var resultado = userService.getByUserName("titagena@gmail.com");

        Assertions.assertEquals(user1.getPassword(), resultado.get().getPassword());
    }

    @Test
    void existsByUserName() {
        var user1 = new User();
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user1.setIdUser("001");
        user1.setUsername("titagena@gmail.com");
        user1.setPassword("123456");
        user1.setRoles(roles);
        Mockito.when(userRepository.existsByUserName("titagena@gmail.com")).thenReturn(true);

        var resultado = userService.existsByUserName("titagena@gmail.com");

        Assertions.assertTrue(resultado);
    }

    @Test
    void save() {
        var user1 = new User();
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user1.setIdUser("001");
        user1.setUsername("titagena@gmail.com");
        user1.setPassword("123456");
        user1.setRoles(roles);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);

        Assertions.assertEquals(0,0);
    }

    @Test
    void getUserByUserName() {
        var user1 = new User();
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user1.setIdUser("001");
        user1.setUsername("titagena@gmail.com");
        user1.setPassword("123456");
        user1.setRoles(roles);
        Mockito.when(userRepository.findUserByUserName("titagena@gmail.com")).thenReturn(user1);

        var resultado = userService.getUserByUserName("titagena@gmail.com");

        Assertions.assertEquals(user1.getIdUser(), resultado.getIdUser());

    }
}