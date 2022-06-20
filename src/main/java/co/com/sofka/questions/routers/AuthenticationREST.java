package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AuthResponse;
import co.com.sofka.questions.model.MapperUser;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.model.AuthRequest;
import co.com.sofka.questions.security.JWTUtil;
import co.com.sofka.questions.security.PBKDF2Encoder;

import co.com.sofka.questions.service.UserService;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/users")
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private final UserService userService1;
    private final MapperUser mapperUser;



    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest login) {
        return userService1.getUserByUsername(login.getUsername())
                .filter(userDetails -> (login.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }


    @PostMapping("/register")
    public Mono<ResponseEntity> register(@RequestBody UserDTO introUser) {
       return userService.getUserByUsername(introUser.getUsername()).flatMap(user -> {
            if (user.getUsername() == introUser.getUsername()) {
                return Mono.just(new ResponseEntity("Usuario ya registrado", HttpStatus.BAD_REQUEST));
            }
            return userService.save(mapperUser.mapperToUserInto().apply(introUser))
                    .map(element -> ResponseEntity.created(URI.create("/users".concat(element.getId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(element));
       });
    }
}
