package com.example.lab4server.service.user;

import com.example.lab4server.DTO.user.TokenResponse;
import com.example.lab4server.DTO.user.UserRequest;
import com.example.lab4server.entity.UserEntity;
import com.example.lab4server.repository.user.UserRepository;
import com.example.lab4server.sequrity.service.jwt.JWTTokenService;
import com.example.lab4server.sequrity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenService jwtTokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JWTTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void register(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public TokenResponse login(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String jwtToken = jwtTokenService.generateToken(userId);
        return TokenResponse.builder().userId(userId).jwtToken(jwtToken).build();
    }
}
