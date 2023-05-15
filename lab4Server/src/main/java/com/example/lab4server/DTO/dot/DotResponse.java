package com.example.lab4server.DTO.dot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DotResponse {
    private Long id;
    private double x;
    private double y;
    private double r;
    private String result;
    private Long computationTime;
    private Instant checkTime;
}
