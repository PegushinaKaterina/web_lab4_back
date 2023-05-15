package com.example.lab4server.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "dots")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private double r;

    @Column(name = "result")
    private String result;

    @Column(name = "computation_time")
    private Long computationTime;

    @Column(name = "check_time")
    private OffsetDateTime checkTime;

    @Column(name = "user_id")
    private Long userId;
}
