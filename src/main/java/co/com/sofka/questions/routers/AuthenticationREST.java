package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.MapperUser;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.model.security.JWTUtil;
import co.com.sofka.questions.model.security.PBKDF2Encoder;
import co.com.sofka.questions.service.UserService;
import co.com.sofka.questions.service.UserService1;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private UserService1 userService1;
    private final MapperUser mapperUser;


//    @PostMapping("/login")
//    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
//        System.out.println(ar);
//        return userService1.getUserByUsername(ar.getUsername())
//                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
//                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails),userDetails.getUsername())))
//                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
//    }

    @PostMapping("/register")
    public  Mono<ResponseEntity<String>> register(@Valid @RequestBody UserDTO introUser) {
        userService1.save(mapperUser.mapperToUserInto(null).apply(introUser));
       return Mono.just( ResponseEntity.ok("Usuario registrado"));
    }
}


//      if(bindingResult.hasErrors())
//          return new ResponseEntity("email invalido o campos mal diligenciados", HttpStatus.BAD_REQUEST);
//       if(userService1.existsByUsername(newUser.getUsername()))
//         return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
// return Mono.just(ResponseEntity.status(HttpStatus.OK).build());-----.status(HttpStatus.OK).build()