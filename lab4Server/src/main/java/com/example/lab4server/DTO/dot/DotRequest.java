package com.example.lab4server.DTO.dot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class DotRequest {

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Double r;
}
