package com.example.lab4server.converter.dot;

import com.example.lab4server.DTO.dot.DotRequest;
import com.example.lab4server.entity.DotEntity;
import org.springframework.stereotype.Component;

@Component("dotRequestControllerBean")
public class DotRequestConverter implements DotConverter<DotRequest, DotEntity> {

    @Override
    public DotEntity convert(DotRequest dotRequest) {
        DotEntity dotEntity = new DotEntity();
        dotEntity.setX(dotRequest.getX());
        dotEntity.setY(dotRequest.getY());
        dotEntity.setR(dotRequest.getR());
        return dotEntity;
    }
}
