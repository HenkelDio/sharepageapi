package com.sharepage.sharepage.controllers;

import com.sharepage.sharepage.dto.CreateUserRequest;
import com.sharepage.sharepage.entities.User;
import com.sharepage.sharepage.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody CreateUserRequest req) {
        return service.createUser(req);
    }
}
