package com.example.lab4server.service.dot;

import com.example.lab4server.DTO.dot.CircleResponse;
import com.example.lab4server.DTO.dot.DotRequest;
import com.example.lab4server.DTO.dot.DotResponse;

import java.util.List;

public interface DotService {
    void saveDot(DotRequest dotRequest, Long userId);
    List<DotResponse> findAllDotsByUserId(Long userId);
    void deletedAllDotsByUserId(Long userId);

    List<CircleResponse> findAllCirclesByUserId(Long userId, Double r);
}
