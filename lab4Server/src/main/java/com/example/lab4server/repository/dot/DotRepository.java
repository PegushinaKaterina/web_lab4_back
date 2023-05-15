package com.example.lab4server.repository.dot;

import com.example.lab4server.entity.DotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DotRepository extends JpaRepository<DotEntity, Long> {
    List<DotEntity> findAllByUserId(Long userId);

    void removeAllByUserId(Long userId);
}
