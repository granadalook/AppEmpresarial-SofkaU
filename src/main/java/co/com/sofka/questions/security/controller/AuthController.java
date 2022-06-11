package co.com.sofka.questions.security.controller;

import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.dto.LoginUser;
import co.com.sofka.questions.security.jwt.JWTAuthResponseDto;
import co.com.sofka.questions.security.repositories.RolRepository;
import co.com.sofka.questions.security.repositories.UserRepository;
import co.com.sofka.questions.security.dto.NewUser;
import co.com.sofka.questions.security.jwt.JwtProvider;
import co.com.sofka.questions.security.services.RolService;
import co.com.sofka.questions.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired(required = true)
    PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    AuthenticationManager authenticationManager;

    @Autowired(required = true)
    UserService userService;

    @Autowired(required = true)
    RolService rolService;

    @Autowired(required = true)
    JwtProvider jwtProvider;

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody NewUser newUser) {
        if(userService.existsByUserName(newUser.getUsername()))
            return new ResponseEntity<>("Ese usuario ya existe", HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName("ROLE_ADMIN"));
        user.setRoles(roles);

        userService.saveUser(user);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginUser loginUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDto(token));
    }


}
