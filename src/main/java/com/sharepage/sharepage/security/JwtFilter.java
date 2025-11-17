package com.sharepage.sharepage.security;

import com.sharepage.sharepage.repositories.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            String email = jwtUtil.getEmail(token);
            req.setAttribute("email", email);
        }

        chain.doFilter(request, response);
    }
}
