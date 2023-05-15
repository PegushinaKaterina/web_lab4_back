package com.example.lab4server.service.dot;

import com.example.lab4server.DTO.dot.CircleResponse;
import com.example.lab4server.DTO.dot.DotRequest;
import com.example.lab4server.DTO.dot.DotResponse;
import com.example.lab4server.converter.dot.DotRequestConverter;
import com.example.lab4server.converter.dot.DotResponseConverter;
import com.example.lab4server.entity.DotEntity;
import com.example.lab4server.repository.dot.DotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class DotServiceImpl implements DotService {

    private final DotRepository dotRepository;
    private final DotRequestConverter dotRequestConverter;
    private final DotResponseConverter dotResponseConverter;
    private final HitChecker hitChecker;

    @Autowired
    public DotServiceImpl(DotRepository dotRepository,
                          DotRequestConverter dotRequestConverter,
                          DotResponseConverter dotResponseConverter,
                          HitChecker hitChecker) {
        this.dotRepository = dotRepository;
        this.dotRequestConverter = dotRequestConverter;
        this.dotResponseConverter = dotResponseConverter;
        this.hitChecker = hitChecker;
    }

    @Override
    public void saveDot(DotRequest dotRequest, Long userId) {
        DotEntity dotEntity = dotRequestConverter.convert(dotRequest);
        long startTime = System.nanoTime();
        dotEntity.setResult(hitChecker.checkHit(dotEntity.getX(), dotEntity.getY(), dotEntity.getR()));
        dotEntity.setComputationTime((System.nanoTime() - startTime)/1000);
        dotEntity.setCheckTime(Instant.now().atOffset(ZoneOffset.UTC));
        dotEntity.setUserId(userId);
        dotRepository.save(dotEntity);
    }

    @Override
    public List<DotResponse> findAllDotsByUserId(Long userId) {
        return dotRepository.findAllByUserId(userId)
                .stream()
                .map(dotResponseConverter::convert)
                .toList();
    }

    @Override
    @Transactional
    public void deletedAllDotsByUserId(Long userId) {
        dotRepository.removeAllByUserId(userId);
    }

    @Override
    public List<CircleResponse> findAllCirclesByUserId(Long userId, Double r) {
        List<DotResponse> dotResponseList = findAllDotsByUserId(userId);
        List<CircleResponse> circleResponseList = new ArrayList<>();
        if (dotResponseList.size() != 0) {
            List<DotResponse> tableWithoutLastDot = dotResponseList.subList(0, dotResponseList.size() - 1);
            DotResponse lastDot = dotResponseList.get(dotResponseList.size() - 1);
            tableWithoutLastDot.forEach(tableDot -> circleResponseList.add(new CircleResponse(tableDot, "black", "white", r)));
            circleResponseList.add(new CircleResponse(lastDot, "red", "red", r));
        }
        return circleResponseList;
    }
}

