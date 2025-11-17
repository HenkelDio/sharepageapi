package com.sharepage.sharepage.services;

import com.sharepage.sharepage.dto.CreateUserRequest;
import com.sharepage.sharepage.entities.User;
import com.sharepage.sharepage.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(CreateUserRequest req) {
        User u = new User();
        u.setEmail(req.email());
        u.setName(req.name());
        u.setPassword(encoder.encode(req.password()));
        return repo.save(u);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow();
    }

    public boolean validatePassword(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }
}
