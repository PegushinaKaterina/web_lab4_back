package com.example.lab4server.converter.dot;

import com.example.lab4server.DTO.dot.DotResponse;
import com.example.lab4server.entity.DotEntity;
import org.springframework.stereotype.Component;

@Component("dotResponseControllerBean")
public class DotResponseConverter implements DotConverter<DotEntity, DotResponse> {

    @Override
    public DotResponse convert(DotEntity dotEntity) {
        return DotResponse.builder()
                .id(dotEntity.getId())
                .x(dotEntity.getX())
                .y(dotEntity.getY())
                .r(dotEntity.getR())
                .result(dotEntity.getResult())
                .computationTime(dotEntity.getComputationTime())
                .checkTime(dotEntity.getCheckTime().toInstant()).build();
    }
}
