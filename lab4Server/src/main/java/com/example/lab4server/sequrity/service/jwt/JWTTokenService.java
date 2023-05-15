package com.example.lab4server.sequrity.service.jwt;

public interface JWTTokenService {
    String generateToken(Long userId);
    Long getUserIdFromToken(String token);
    boolean validateToken(String token);
}
