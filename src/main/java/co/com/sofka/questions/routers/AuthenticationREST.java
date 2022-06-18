package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.MapperUser;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.model.security.AuthRequest;
import co.com.sofka.questions.model.security.JWTUtil;
import co.com.sofka.questions.model.security.PBKDF2Encoder;
import co.com.sofka.questions.model.security.model.AuthResponse;
import co.com.sofka.questions.service.UserService;
import co.com.sofka.questions.service.UserService1;
import lombok.AllArgsConstructor;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private final UserService1 userService1;
    private final MapperUser mapperUser;



    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest login) {

        return userService1.getUserByUsername(login.getUsername())

                .filter(userDetails -> (login.getPassword()).equals(userDetails.getPassword()))

                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails), userDetails.getUsername())))

                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }


    @PostMapping("/register")
    public ResponseEntity<Mono<UserDTO>> register(@RequestBody UserDTO introUser) {

        var registro = userService1.save(mapperUser.mapperToUserInto().apply(introUser)).map(mapperUser.mapperToUserDto());
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }
}


//Mono.just( ResponseEntity.ok("Usuario registrado"));
//      if(bindingResult.hasErrors())
//          return new ResponseEntity("email invalido o campos mal diligenciados", HttpStatus.BAD_REQUEST);
//       if(userService1.existsByUsername(newUser.getUsername()))
//         return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
// return Mono.just(ResponseEntity.status(HttpStatus.OK).build());-----.status(HttpStatus.OK).build()