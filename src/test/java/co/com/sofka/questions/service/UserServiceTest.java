package co.com.sofka.questions.service;

import co.com.sofka.questions.collections.UserInto;
import co.com.sofka.questions.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("JUnit test for save method")
    public void saveTest(){
        var userInto = new UserInto();
        userInto.setId("001");
        userInto.setUsername("titagena@gmail.com");
        userInto.setPassword("123456");

        when(userRepository.save(userInto)).thenReturn(Mono.just(userInto));

        var resultado = userService.save(userInto);

        Assertions.assertNotNull(resultado);

    }

    @Test
    @DisplayName("JUnit test for getUserByUsername method")
    public void getUserByUsernameTest(){
        var userInto = new UserInto();
        userInto.setId("001");
        userInto.setUsername("titagena@gmail.com");
        userInto.setPassword("123456");

        when(userRepository.findUserByUsername("titagena@gmail.com")).thenReturn(Mono.just(userInto));

        Mono<UserInto> resultado = userService.getUserByUsername("titagena@gmail.com");

        Assertions.assertNotNull(resultado);
    }


}