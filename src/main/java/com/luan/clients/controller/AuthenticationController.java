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
import com.luan.clients.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userData = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(userData);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticationDTO data){
        
        if(this.userService.loadUserByUsername(data.login()) != null){
            System.out.println("chegou aqui");
            return ResponseEntity.badRequest().build();
        } 

        String newPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), newPassword);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
