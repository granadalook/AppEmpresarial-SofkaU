package co.com.sofka.questions.routers;

import co.com.sofka.questions.collections.UserInto;
import co.com.sofka.questions.model.AuthRequest;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(AuthenticationREST.class)
public class AuthenticationRESTTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserService userService;



    @Test
    public void register() {
        var userDTO = new UserDTO();
        userDTO.setUsername("titagena@gmail.com");
        userDTO.setPassword("123456");

        var userData = new UserInto();
        userData.setUsername("titagena@gmail.com");
        userData.setPassword("123456");

        Mockito.when(userService.save(userData)).then((Answer<?>) userData);

        webTestClient.post().uri("/register").body(userDTO, UserDTO.class).exchange()
                .expectStatus().isOk();

    }

    @Test
    void login() {
        var loginDTO = new AuthRequest();
        loginDTO.setUsername("titagena@gmail.com");
        loginDTO.setPassword("123456");

        var loginData = new UserInto();
        loginData.setUsername("titagena@gmail.com");
        loginData.setPassword("123456");

        Mockito.when(userService.getUserByUsername("titagena@gmail.com")).thenReturn(Mono.just(loginData));

        webTestClient.post().uri("/login").body(loginDTO, AuthRequest.class).exchange().expectStatus().isOk();

    }

    @Test
    void updatePassword() {
    }
}