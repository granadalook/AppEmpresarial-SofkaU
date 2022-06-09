package co.com.sofka.questions.security.controller;

import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.collections.User;
import co.com.sofka.questions.security.dto.JwtDto;
import co.com.sofka.questions.security.dto.LoginUser;
import co.com.sofka.questions.security.dto.Mensaje;
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
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
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

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal diligenciados o email inválido"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity(new Mensaje("Usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Mensaje("Email ya existe"), HttpStatus.BAD_REQUEST);
        User user =
                new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName("ADMIN").get());
        user.setRoles(roles);
        userService.saveUser(user);
        return new ResponseEntity(new Mensaje("Usuario guardado con exito."), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
