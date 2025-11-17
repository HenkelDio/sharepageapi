package com.sharepage.sharepage.controllers;

import com.sharepage.sharepage.dto.LoginRequest;
import com.sharepage.sharepage.dto.LoginResponse;
import com.sharepage.sharepage.entities.User;
import com.sharepage.sharepage.security.JwtUtil;
import com.sharepage.sharepage.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwt;

    public AuthController(UserService userService, JwtUtil jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        User u = userService.findByEmail(req.email());

        if (!userService.validatePassword(req.password(), u.getPassword()))
            throw new RuntimeException("Senha inválida");

        return new LoginResponse(jwt.generateToken(u.getEmail()));
    }
}