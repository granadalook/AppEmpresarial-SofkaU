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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity("email invalido o campos mal diligenciados", HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getUserName(), passwordEncoder.encode(newUser.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDto> login(@Valid @RequestBody LoginUser loginUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JWTAuthResponseDto jwtDto = new JWTAuthResponseDto(token, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }


}
