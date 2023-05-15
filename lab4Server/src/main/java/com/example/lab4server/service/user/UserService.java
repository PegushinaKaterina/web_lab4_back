package com.example.lab4server.service.user;

import com.example.lab4server.DTO.user.TokenResponse;
import com.example.lab4server.DTO.user.UserRequest;

public interface UserService {
    void register(UserRequest userRequest);
    TokenResponse login(UserRequest userRequest);
}
