package com.example.lab4server.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
