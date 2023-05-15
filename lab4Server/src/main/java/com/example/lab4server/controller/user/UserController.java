package com.example.lab4server.controller.user;

import com.example.lab4server.DTO.user.TokenResponse;
import com.example.lab4server.DTO.user.UserRequest;
import com.example.lab4server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @NotNull @Valid UserRequest userRequest) {
        userService.register(userRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @NotNull @Valid UserRequest userRequest) {
        return userService.login(userRequest);
    }
}
