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
import java.util.concurrent.atomic.AtomicReference;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private final UserService userService1;
    private final MapperUser mapperUser;



    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest login) {
        return userService.getUserByUsername(login.getUsername())
                .filter(userDetails -> (login.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails), userDetails.getUsername())))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }


    @PostMapping("/register")
    public ResponseEntity<Mono<UserDTO>> register(@RequestBody UserDTO introUser) {
        var registro = userService.save(mapperUser.mapperToUserInto().apply(introUser)).map(mapperUser.mapperToUserDto());
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

   @PutMapping("/update")
    public Mono<ResponseEntity<Void>> updatePassword(@RequestBody UserDTO userUpdate) {
        return userService.getUserByUsername(userUpdate.getUsername()).flatMap(element -> {
            element.setPassword(userUpdate.getPassword());
            return userService.save(element).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }



}
