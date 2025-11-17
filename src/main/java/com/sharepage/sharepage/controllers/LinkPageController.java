package com.sharepage.sharepage.controllers;

import com.sharepage.sharepage.dto.CreatePageRequest;
import com.sharepage.sharepage.entities.LinkPage;
import com.sharepage.sharepage.entities.User;
import com.sharepage.sharepage.services.LinkPageService;
import com.sharepage.sharepage.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linkpages")
@CrossOrigin
public class LinkPageController {

    private final LinkPageService linkService;
    private final UserService userService;

    public LinkPageController(LinkPageService linkService, UserService userService) {
        this.linkService = linkService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public List<LinkPage> myPages(HttpServletRequest req) {
        String email = (String) req.getAttribute("email");
        User user = userService.findByEmail(email);
        return linkService.list(user);
    }

    @PostMapping
    public LinkPage create(@RequestBody CreatePageRequest req, HttpServletRequest http) {
        String email = (String) http.getAttribute("email");
        User user = userService.findByEmail(email);
        return linkService.create(req, user);
    }

    @GetMapping("/{id}")
    public LinkPage getOne(@PathVariable Long id) {
        return linkService.get(id);
    }
}
