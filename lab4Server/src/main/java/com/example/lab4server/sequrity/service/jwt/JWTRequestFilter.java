package com.example.lab4server.sequrity.service.jwt;

import com.example.lab4server.sequrity.service.SecurityToken;
import com.example.lab4server.sequrity.service.SecurityUser;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    private final static String TOKEN_PREFIX = "Bearer ";
    private final JWTTokenService jwtTokenService;
    public JWTRequestFilter(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    private static String getTokenFromHeader(HttpServletRequest request) {
        String tokenHeaderFromRequest = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenHeaderFromRequest != null && tokenHeaderFromRequest.startsWith(TOKEN_PREFIX)) {
            return tokenHeaderFromRequest.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);

        if (token != null) {

            try {
                if (jwtTokenService.validateToken(token)) {
                    Long userId = jwtTokenService.getUserIdFromToken(token);
                    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityToken securityToken = new SecurityToken(new SecurityUser(userId));
                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(securityToken);
                    }
                }
            } catch (UsernameNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }

        chain.doFilter(request, response);
    }
}
