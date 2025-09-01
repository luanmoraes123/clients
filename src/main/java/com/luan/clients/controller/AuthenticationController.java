package com.luan.clients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.clients.model.AuthenticationDTO;
import com.luan.clients.model.User;
import com.luan.clients.repository.UserRepository;
import com.luan.clients.security.TokenService;
import com.luan.clients.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data){
        var userData = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(userData);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AuthenticationDTO data){
        if(this.userService.loadUserByUsername(data.login()) != null){
            return ResponseEntity.badRequest().build();
        } 

        String newPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), newPassword);


        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
