package co.com.sofka.questions.routers;


import co.com.sofka.questions.model.security.AuthRequest;
import co.com.sofka.questions.model.security.JWTUtil;
import co.com.sofka.questions.model.security.PBKDF2Encoder;
import co.com.sofka.questions.model.security.model.AuthResponse;
  import co.com.sofka.questions.service.UserService;
import co.com.sofka.questions.service.UserService1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService1 userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        System.out.println();
        return userService.getUserByUserName(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails),userDetails.getUsername())))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
