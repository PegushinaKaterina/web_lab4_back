package com.example.lab4server.sequrity.service.jwt;

import com.example.lab4server.sequrity.service.jwt.JWTTokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTTokenServiceImpl implements JWTTokenService {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Value("${jwt.validity-in-minutes}")
    private Integer tokenValidityInMinutes;
    @Override
    public String generateToken(Long userId) {
        Date currentDate = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + tokenValidityInMinutes*60000))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(
            Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
